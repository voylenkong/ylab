package local.vng.lesson04.eventsourcing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import local.vng.lesson04.eventsourcing.Person;
import local.vng.lesson04.eventsourcing.message.MQCreatePerson;
import local.vng.lesson04.eventsourcing.message.MQDeletePerson;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * PersonApiImpl
 * <p>
 * Имплементация {@link PersonApi}
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class PersonApiImpl implements PersonApi {

    private DataSource dataSource;
    private ConnectionFactory connectionFactory;

    private static final String SELECT_PERSON_BY_ID = "SELECT * FROM person WHERE person_id = ?";
    private static final String SELECT_PERSON_ALL = "SELECT * FROM person";
    private static final String MQ_EXCHANGE_NAME = "exc";
    private static final String MQ_DELETE_QUEUE_NAME = "deletePerson";
    private static final String MQ_DELETE_ROUTING_KEY = "del";
    private static final String MQ_CREATE_QUEUE_NAME = "createPerson";
    private static final String MQ_CREATE_ROUTING_KEY = "crt";

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
            channel.queueDeclare(MQ_DELETE_QUEUE_NAME, true, false, false, null);
            channel.queueBind(MQ_DELETE_QUEUE_NAME, MQ_EXCHANGE_NAME, MQ_DELETE_ROUTING_KEY);

            ObjectMapper objectMapper = new ObjectMapper();
            MQDeletePerson mQDeletePerson = new MQDeletePerson(personId);
            String message = objectMapper.writeValueAsString(mQDeletePerson);

            channel.basicPublish(MQ_EXCHANGE_NAME, MQ_DELETE_ROUTING_KEY, null, message.getBytes());
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
            channel.queueDeclare(MQ_CREATE_QUEUE_NAME, true, false, false, null);
            channel.queueBind(MQ_CREATE_QUEUE_NAME, MQ_EXCHANGE_NAME, MQ_CREATE_ROUTING_KEY);

            ObjectMapper objectMapper = new ObjectMapper();
            MQCreatePerson mqCreatePerson = new MQCreatePerson(personId, firstName, lastName, middleName);
            String message = objectMapper.writeValueAsString(mqCreatePerson);

            channel.basicPublish(MQ_EXCHANGE_NAME, MQ_CREATE_ROUTING_KEY, null, message.getBytes());
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
        var result = new ArrayList<Person>();
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
