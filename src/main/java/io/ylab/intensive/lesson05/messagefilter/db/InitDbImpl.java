package io.ylab.intensive.lesson05.messagefilter.db;

import io.ylab.intensive.lesson05.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * InitDbImpl
 * <p>
 * Имплементация {@link InitDb}
 *
 * @author VoylenkoNG
 * 01.04.2023
 */
@Component
public class InitDbImpl implements InitDb {

    private DataSource dataSource;
    private static final String TABLE_NAME = "bad_words";
    private static final String INSERT_WORD = "INSERT INTO bad_words (word) VALUES(?)";

    @Autowired
    public InitDbImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void initDb(File file) {
        String dQuery;
        try {
            if (isTableExist(TABLE_NAME)) {
                dQuery = "delete from " + TABLE_NAME;
            } else {
                dQuery = "create table if not exists "
                        + TABLE_NAME
                        + "(\n"
                        + "\tword varchar\n"
                        + ")";
            }

            DbUtil.applyDdl(dQuery, this.dataSource);
            fillTableFromFile(file);
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Проверка факта наличия таблицы в БД
     *
     * @param tableName Имя таблицы
     * @return Результат boolean
     * @throws SQLException
     */
    private boolean isTableExist(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = this.dataSource.getConnection().getMetaData();
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null)) {
            return resultSet.next();
        }
    }

    /**
     * Вставка данных в таблицу из файла
     *
     * @param file Имя файла
     * @throws SQLException
     * @throws IOException
     */
    private void fillTableFromFile(File file) throws SQLException, IOException {
        List<String> words = readFromFile(file);
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORD);
        ) {
            for (String word : words) {
                if (nonNull(word)) {
                    preparedStatement.setString(1, word.toLowerCase());
                }
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        }
    }

    /**
     * Чтение из файла
     *
     * @param file Имя файла
     * @return
     * @throws IOException
     */
    private List<String> readFromFile(File file) throws IOException {
        List<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String line = br.readLine();

            while (Objects.nonNull(line)) {
                result.add(line);
                line = br.readLine();
            }
        }
        return result;
    }
}
