package io.ylab.intensive.lesson05.sqlquerybuilder;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SQLQueryExtenderTest {
  public static void main(String[] args) throws SQLException {
    BasicConfigurator.configure();

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    applicationContext.start();

    SQLQueryBuilder queryBuilder = applicationContext.getBean(SQLQueryBuilder.class);
    List<String> tables = queryBuilder.getTables();

    System.out.println("Запросы вида “SELECT <col1>, <col2>, <col3> FROM <tablename>” ко всем таблицам БД");
    System.out.println();

    for (String tableName : tables) {
      System.out.println(queryBuilder.queryForTable(tableName));
    }

    applicationContext.close();
  }
}
