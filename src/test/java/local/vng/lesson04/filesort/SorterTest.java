package local.vng.lesson04.filesort;

import local.vng.lesson04.DbUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * FileSorterTest
 *
 * @author VoylenkoNG
 * 26.03.2023
 */
public class SorterTest {

    @BeforeEach
    public void init() {

        File dataTestFile = new File("src\\main\\resources\\dataTest.txt");

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(dataTestFile));
        ) {
            long[] initData = new long[]{234, -90, 500, 0, 33, 33, 3};
            for (long elem : initData) {
                bw.write(String.valueOf(elem));
                bw.newLine();
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        try {
            var fileSorter = new FileSortImpl(initDb());
            fileSorter.sort(dataTestFile);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void end() {
        File dataTestFile = new File("src\\main\\resources\\dataTest.txt");
        File sortedDataTestFile = new File("src\\main\\resources\\batchSorted.txt");

        dataTestFile.delete();
        sortedDataTestFile.delete();
    }

    @Test
    public void testSortDB() {
        File sortedDataTestFile = new File("src\\main\\resources\\batchSorted.txt");

        try (
                BufferedReader br = new BufferedReader(new FileReader(sortedDataTestFile));
        ) {

            var sortedReadData = new ArrayList<Long>();
            String line = br.readLine();
            while (Objects.nonNull(line)) {
                sortedReadData.add(Long.valueOf(line));
                line = br.readLine();
            }

            assertAll(
                    () -> assertEquals(500, sortedReadData.get(0)),
                    () -> assertEquals(234, sortedReadData.get(1)),
                    () -> assertEquals(33, sortedReadData.get(2)),
                    () -> assertEquals(33, sortedReadData.get(3)),
                    () -> assertEquals(3, sortedReadData.get(4)),
                    () -> assertEquals(0, sortedReadData.get(5)),
                    () -> assertEquals(-90, sortedReadData.get(6))
            );
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    private static DataSource initDb() throws SQLException {
        String createSortTable = ""
                + "drop table if exists numbers;"
                + "CREATE TABLE if not exists numbers (\n"
                + "\tval bigint\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createSortTable, dataSource);
        return dataSource;
    }
}
