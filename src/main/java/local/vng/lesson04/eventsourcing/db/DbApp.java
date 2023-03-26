package local.vng.lesson04.eventsourcing.db;

import java.sql.SQLException;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import local.vng.lesson04.DbUtil;
import local.vng.lesson04.RabbitMQUtil;
import local.vng.lesson04.eventsourcing.message.MQCreatePerson;
import local.vng.lesson04.eventsourcing.message.MQDeletePerson;
import org.apache.log4j.BasicConfigurator;

import static java.util.Objects.nonNull;

/**
 * DbApp
 * <p>
 * Принимает из RabbitMQ сообщения о добавлении/удалении данных, затем выполняет
 * в БД соответствующие запросы.
 * Необходимо, чтобы сообщения, отправленные первыми, обрабатывались также
 * первыми (чтобы принцип FIFO не нарушался).
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class DbApp {
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        DataSource dataSource = initDb();
        ConnectionFactory connectionFactory = initMQ();

        DbService dbService = new DbService(dataSource);

        String queueDelete = "deletePerson";
        String queueCreate = "createPerson";

        try (com.rabbitmq.client.Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            while (!Thread.currentThread().isInterrupted()) {
                GetResponse messageDelete = channel.basicGet(queueDelete, true);
                GetResponse messageCreate = channel.basicGet(queueCreate, true);

                if (nonNull(messageDelete)) {
                    String receivedDelete = new String(messageDelete.getBody());

                    ObjectMapper objectMapper = new ObjectMapper();
                    MQDeletePerson mqDeletePerson = objectMapper.readValue(receivedDelete, MQDeletePerson.class);

                    dbService.deletePerson(mqDeletePerson.getId());
                }

                if (nonNull(messageCreate)) {
                    String receivedCreate = new String(messageCreate.getBody());

                    ObjectMapper objectMapper = new ObjectMapper();
                    MQCreatePerson mqCreatePerson = objectMapper.readValue(receivedCreate, MQCreatePerson.class);

                    dbService.savePerson(
                            mqCreatePerson.getId(),
                            mqCreatePerson.getName(),
                            mqCreatePerson.getLastName(),
                            mqCreatePerson.getMiddleName());
                }
            }
        }
    }

    /**
     * Инициализация MQ
     *
     * @return ConnectionFactory
     * @throws Exception
     */
    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactory();
    }

    /**
     * Инициализация DB
     *
     * @return Datasource
     * @throws SQLException
     */
    private static DataSource initDb() throws SQLException {
        String ddl = "drop table if exists person;"
                + "create table if not exists person (\n"
                + "\tperson_id bigint primary key,\n"
                + "\tfirst_name varchar,\n"
                + "\tlast_name varchar,\n"
                + "\tmiddle_name varchar\n"
                + ")";

        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(ddl, dataSource);
        return dataSource;
    }
}
