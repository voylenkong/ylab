package local.vng.lesson05.messagefilter;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import local.vng.lesson05.messagefilter.db.InitDbImpl;
import local.vng.lesson05.messagefilter.mq.InitMqImpl;
import local.vng.lesson05.messagefilter.service.CensorService;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static java.util.Objects.nonNull;

public class MessageFilterApp {

    private static final String MQ_EXCHANGE_NAME = "exc";
    private static final String MQ_INPUT_QUEUE = "input";
    private static final String MQ_OUTPUT_QUEUE = "output";
    private static final String MQ_INPUT_ROUTING_KEY = "inputKey";
    private static final String MQ_OUTPUT_ROUTING_KEY = "outputKey";

    public static void main(String[] args) throws IOException, TimeoutException {
        BasicConfigurator.configure();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла со списком нецензурных слов для загрузки в БД: ");
        String file = scanner.nextLine();

        InitDbImpl initDb = applicationContext.getBean(InitDbImpl.class);
        initDb.initDb(new File(file));

        InitMqImpl initMq = applicationContext.getBean(InitMqImpl.class);
        initMq.initMq(MQ_EXCHANGE_NAME, MQ_INPUT_QUEUE, MQ_INPUT_ROUTING_KEY);

        ConnectionFactory connectionFactory = applicationContext.getBean(ConnectionFactory.class);
        CensorService service = applicationContext.getBean(CensorService.class);

        try (com.rabbitmq.client.Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            while (!Thread.currentThread().isInterrupted()) {
                GetResponse message = channel.basicGet(MQ_INPUT_QUEUE, true);

                if (nonNull(message)) {
                    String censoredMessageFromInput = service.censorSentence(new String(message.getBody()));

                    channel.exchangeDeclare(MQ_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
                    channel.queueDeclare(MQ_OUTPUT_QUEUE, true, false, false, null);
                    channel.queueBind(MQ_OUTPUT_QUEUE, MQ_EXCHANGE_NAME, MQ_OUTPUT_ROUTING_KEY);
                    channel.basicPublish(MQ_EXCHANGE_NAME, MQ_OUTPUT_ROUTING_KEY, null, censoredMessageFromInput.getBytes());

                    System.out.println(censoredMessageFromInput);
                }
            }
        }

        scanner.close();
        applicationContext.close();
    }
}
