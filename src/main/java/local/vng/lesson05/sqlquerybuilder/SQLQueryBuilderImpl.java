package local.vng.lesson05.sqlquerybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLQueryBuilderImpl
 * <p>
 * Имплементация {@link SQLQueryBuilder}
 *
 * @author VoylenkoNG
 * 01.04.2023
 */

@Component
public class SQLQueryBuilderImpl implements SQLQueryBuilder {

    private final DataSource dataSource;

    @Autowired
    public SQLQueryBuilderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String queryForTable(String tableName) throws SQLException {
        if (isTableExist(tableName)) {
            StringBuilder resultSb = new StringBuilder();
            resultSb.append("SELECT ");

            DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();

            try (ResultSet resultSet = databaseMetaData.getColumns(null, null, tableName, null)) {
                resultSet.next();
                resultSb.append(resultSet.getString("COLUMN_NAME"));
                while (resultSet.next()) {
                    resultSb.append(", ");
                    resultSb.append(resultSet.getString("COLUMN_NAME"));
                }
                resultSb.append(" FROM ");
                resultSb.append(tableName);
            }
            return resultSb.toString();
        } else {
            return null;
        }
    }

    @Override
    public List<String> getTables() throws SQLException {
        List<String> resultTables = new ArrayList<>();
        DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();

        /*Возвращает в качестве результата список имен всех таблиц, которые есть в БД.
        В том числе и системные!
        */
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE", "SYSTEM TABLE"})) {
            while (resultSet.next()) {
                resultTables.add(resultSet.getString("TABLE_NAME"));
            }
        }
        return resultTables;
    }

    /**
     * Проверка существует ли таблица в БД
     *
     * @param tableName Имя таблицы
     * @return Результат проверки Boolean
     * @throws SQLException
     */
    private boolean isTableExist(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null)) {
            return resultSet.next();
        }
    }
}
