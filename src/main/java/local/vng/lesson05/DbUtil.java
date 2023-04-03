package local.vng.lesson05;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

/**
 * DbUtil
 * Инициализация БД
 *
 * @author YLab
 * 25.03.2023
 */
public class DbUtil {

    public static void applyDdl(String ddl, DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(ddl);
        }
    }

    /*
     * Настройки подключения НЕ МЕНЯЕМ!
     * Надо настроить БД таким образом, чтобы она работала со следующими
     * настройками
     */
    public static DataSource buildDataSource() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        dataSource.setDatabaseName("postgres");
        dataSource.setPortNumber(5432);
        dataSource.getConnection().close();
        return dataSource;
    }
}
