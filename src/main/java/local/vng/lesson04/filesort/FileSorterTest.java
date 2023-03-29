package local.vng.lesson04.filesort;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.DataSource;

import local.vng.lesson04.DbUtil;

/**
 * FileSorterTest
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class FileSorterTest {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Путь к файлу для сортировки: ");
        String filePath = scanner.next();
        File data = new File(filePath);

        System.out.println("batch    - вставка в БД batch");
        System.out.println("nonBatch - вставка в БД nonBatch");
        System.out.println("Выберите тип вставки данных в БД: ");
        String operation = scanner.next();

        if (operation.equals("batch")) {
            DataSource dataSource = initDb();

            long start = System.currentTimeMillis();

            FileSorter fileSorter = new FileSortImpl(dataSource);
            File res = fileSorter.sort(data);

            long finish = System.currentTimeMillis();
            System.out.println("Время сортировки со вставкой в БД(batch): " + (finish - start) / 1000 + " секунд");
            System.out.println("Отсортированный файл: " + res.getAbsoluteFile());
        } else if (operation.equals("nonBatch")) {
            DataSource dataSource = initDb();

            long start = System.currentTimeMillis();

            FileSorter fileSorter = new FileSortImpl(dataSource);
            File res = fileSorter.sortNotBatch(data);

            long finish = System.currentTimeMillis();
            System.out.println("Время сортировки со вставкой в БД(nonBatch): " + (finish - start) / 1000 + " секунд");
            System.out.println("Отсортированный файл: " + res.getAbsoluteFile());
        } else {
            System.out.println("Недопустимая команда");
        }

        scanner.close();
    }

    /**
     * Инициализация DB
     *
     * @return Datasource
     * @throws SQLException
     */
    public static DataSource initDb() throws SQLException {
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
