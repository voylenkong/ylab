package local.vng.lesson05.eventsourcing.db;

import java.sql.SQLException;
import javax.sql.DataSource;

import com.rabbitmq.client.ConnectionFactory;
import local.vng.lesson05.DbUtil;
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
  public DataSource dataSource() throws SQLException {
    PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setServerName("localhost");
    dataSource.setUser("postgres");
    dataSource.setPassword("postgres");
    dataSource.setDatabaseName("postgres");
    dataSource.setPortNumber(5432);

    String ddl = "drop table if exists person;"
            + "create table if not exists person (\n"
            + "\tperson_id bigint primary key,\n"
            + "\tfirst_name varchar,\n"
            + "\tlast_name varchar,\n"
            + "\tmiddle_name varchar\n"
            + ")";

    DbUtil.applyDdl(ddl, dataSource);
    
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
