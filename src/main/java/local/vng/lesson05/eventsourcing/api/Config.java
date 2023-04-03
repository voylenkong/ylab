package local.vng.lesson05.eventsourcing.api;

import javax.sql.DataSource;

import com.rabbitmq.client.ConnectionFactory;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Config
 * <p>
 *
 * @author VoylenkoNG
 * 01.04.2023
 */
@Configuration
@ComponentScan("local.vng.lesson05.eventsourcing")
public class Config {

    /**
     * Datasource
     *
     * @return Datasource {@link DataSource}
     */
    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        dataSource.setDatabaseName("postgres");
        dataSource.setPortNumber(5432);
        return dataSource;
    }

    /**
     * ConnectionFactory
     *
     * @return ConnectionFactory {@link ConnectionFactory}
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }
}
