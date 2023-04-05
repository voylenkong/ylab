package io.ylab.intensive.lesson05.eventsourcing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson05.eventsourcing.Person;
import io.ylab.intensive.lesson05.eventsourcing.message.MQCreatePerson;
import io.ylab.intensive.lesson05.eventsourcing.message.MQDeletePerson;
import io.ylab.intensive.lesson05.eventsourcing.message.MqType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * PersonApiImpl
 * <p>
 * Имплементация {@link PersonApi}
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
@Component
public class PersonApiImpl implements PersonApi {

    private DataSource dataSource;
    private ConnectionFactory connectionFactory;
    private static final String SELECT_PERSON_BY_ID = "SELECT * FROM person WHERE person_id = ?";
    private static final String SELECT_PERSON_ALL = "SELECT * FROM person";
    private static final String MQ_EXCHANGE_NAME = "exc";
    private static final String MQ_QUEUE_NAME = "person";
    private static final String MQ_ROUTING_KEY = "key";

    @Autowired
    public PersonApiImpl(DataSource dataSource, ConnectionFactory connectionFactory) {
        this.dataSource = dataSource;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void deletePerson(Long personId) {
        try (com.rabbitmq.client.Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            channel.exchangeDeclare(MQ_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(MQ_QUEUE_NAME, true, false, false, null);
            channel.queueBind(MQ_QUEUE_NAME, MQ_EXCHANGE_NAME, MQ_ROUTING_KEY);

            ObjectMapper objectMapper = new ObjectMapper();
            MQDeletePerson mQDeletePerson = new MQDeletePerson(personId);
            String message = objectMapper.writeValueAsString(mQDeletePerson);

            Map<String, Object> headers = new HashMap<>();
            headers.put("mqType", MqType.DELETE.name());

            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder().headers(headers).build();
            channel.basicPublish(MQ_EXCHANGE_NAME, MQ_ROUTING_KEY, basicProperties, message.getBytes());
        } catch (IOException | TimeoutException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void savePerson(Long personId, String firstName, String lastName, String middleName) {
        try (com.rabbitmq.client.Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            channel.exchangeDeclare(MQ_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(MQ_QUEUE_NAME, true, false, false, null);
            channel.queueBind(MQ_QUEUE_NAME, MQ_EXCHANGE_NAME, MQ_ROUTING_KEY);

            ObjectMapper objectMapper = new ObjectMapper();
            MQCreatePerson mqCreatePerson = new MQCreatePerson(personId, firstName, lastName, middleName);
            String message = objectMapper.writeValueAsString(mqCreatePerson);

            Map<String, Object> headers = new HashMap<>();
            headers.put("mqType", MqType.CREATE.name());

            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder().headers(headers).build();

            channel.basicPublish(MQ_EXCHANGE_NAME, MQ_ROUTING_KEY, basicProperties, message.getBytes());
        } catch (IOException | TimeoutException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Person findPerson(Long personId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PERSON_BY_ID);
        ) {
            ps.setLong(1, personId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String middleName = resultSet.getString(4);
                return new Person(id, name, lastName, middleName);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        List<Person> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PERSON_ALL);
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String middleName = resultSet.getString(4);
                result.add(new Person(id, name, lastName, middleName));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
