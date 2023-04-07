package io.ylab.intensive.lesson05.sqlquerybuilder;

import javax.sql.DataSource;

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
@ComponentScan("io.ylab.intensive.lesson05.sqlquerybuilder")
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
}
