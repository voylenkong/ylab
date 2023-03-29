package local.vng.lesson04.filesort;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;

/**
 * FileSortImpl
 * <p>
 * Имплементация {@link FileSorter}
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class FileSortImpl implements FileSorter {
    private DataSource dataSource;

    private static final String INSERT_NUMBER = "INSERT INTO numbers (val) VALUES(?)";
    private static final String SELECT_SORTED_NUMBERS = "SELECT val FROM numbers ORDER BY val DESC";

    public FileSortImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public File sort(File data) {
        List<Long> buffer = readFileToList(data);

        insertDataBatchToDb(buffer, dataSource);

        File fileOutputResult = new File(data.getAbsoluteFile().getParent() + "/batchSorted.txt");
        return writeDescSortedData(fileOutputResult, dataSource);
    }

    @Override
    public File sortNotBatch(File data) {
        List<Long> buffer = readFileToList(data);

        insertDataToDb(buffer, dataSource);

        File fileOutputResult = new File(data.getAbsoluteFile().getParent() + "/notBatchSorted.txt");
        return writeDescSortedData(fileOutputResult, dataSource);
    }

    /**
     * Чтение данных с файла в List
     *
     * @param data Входной файл
     * @return List с данными с входного файла
     */
    private static List<Long> readFileToList(File data) {
        List<Long> buffer = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(data));) {
            String line = br.readLine();

            while (Objects.nonNull(line)) {
                buffer.add(Long.valueOf(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return buffer;
    }

    /**
     * Вставка данных с List в БД с использованием batch-processing
     *
     * @param buffer     Входной List
     * @param dataSource Datasource
     */
    private static void insertDataBatchToDb(List<Long> buffer, DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_NUMBER);
        ) {
            for (Long number : buffer) {
                ps.setLong(1, number);
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Вставка данных с List в БД без использования batch-processing
     *
     * @param buffer     Входной List
     * @param dataSource Datasource
     */
    private static void insertDataToDb(List<Long> buffer, DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_NUMBER);
        ) {
            for (Long number : buffer) {
                ps.setLong(1, number);
                ps.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Сортировка данных средствами БД в обратном порядке и вывод результата в файл
     *
     * @param fileOutputResult Файл с результатом
     * @param dataSource       Datasource
     * @return Файл с результатом
     */
    private static File writeDescSortedData(File fileOutputResult, DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_SORTED_NUMBERS);
             BufferedWriter bwResult = new BufferedWriter(new FileWriter(fileOutputResult));
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                bwResult.write(Long.toString(resultSet.getLong(1)));
                bwResult.newLine();
            }
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return fileOutputResult;
    }
}
