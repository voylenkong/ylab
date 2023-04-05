package io.ylab.intensive.lesson05.eventsourcing.db;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        DbService dbService = applicationContext.getBean(DbService.class);
        ConnectionFactory connectionFactory = applicationContext.getBean(ConnectionFactory.class);

        String queue = "person";

        try (com.rabbitmq.client.Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            while (!Thread.currentThread().isInterrupted()) {
                GetResponse message = channel.basicGet(queue, true);

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
