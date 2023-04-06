package io.ylab.intensive.lesson05.eventsourcing.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import io.ylab.intensive.lesson05.eventsourcing.message.MQCreatePerson;
import io.ylab.intensive.lesson05.eventsourcing.message.MQDeletePerson;
import io.ylab.intensive.lesson05.eventsourcing.message.MqType;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.util.Objects.nonNull;
import static io.ylab.intensive.lesson05.eventsourcing.message.MqType.CREATE;
import static io.ylab.intensive.lesson05.eventsourcing.message.MqType.DELETE;

public class DbApp {

    private static final String MQ_EXCHANGE_NAME = "exc";
    private static final String MQ_QUEUE_NAME = "person";
    private static final String MQ_ROUTING_KEY = "key";

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        DbService dbService = applicationContext.getBean(DbService.class);
        ConnectionFactory connectionFactory = applicationContext.getBean(ConnectionFactory.class);

        try (com.rabbitmq.client.Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            channel.exchangeDeclare(MQ_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(MQ_QUEUE_NAME, true, false, false, null);
            channel.queueBind(MQ_QUEUE_NAME, MQ_EXCHANGE_NAME, MQ_ROUTING_KEY);

            while (!Thread.currentThread().isInterrupted()) {
                GetResponse message = channel.basicGet(MQ_QUEUE_NAME, true);

                if (nonNull(message)) {
                    MqType mqType = MqType.valueOf(message.getProps().getHeaders().get("mqType").toString());
                    String received = new String(message.getBody());
                    ObjectMapper objectMapper = new ObjectMapper();
                    if (DELETE.equals(mqType)) {

                        MQDeletePerson mqDeletePerson = objectMapper.readValue(received, MQDeletePerson.class);
                        dbService.deletePerson(mqDeletePerson.getId());

                    } else if (CREATE.equals(mqType)) {

                        MQCreatePerson mqCreatePerson = objectMapper.readValue(received, MQCreatePerson.class);
                        dbService.savePerson(
                                mqCreatePerson.getId(),
                                mqCreatePerson.getName(),
                                mqCreatePerson.getLastName(),
                                mqCreatePerson.getMiddleName());
                    }
                }
            }
        }
    }
}
