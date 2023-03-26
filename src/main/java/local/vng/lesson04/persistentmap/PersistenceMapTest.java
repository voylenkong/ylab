package local.vng.lesson04.persistentmap;

import local.vng.lesson04.DbUtil;

import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.DataSource;

/**
 * PersistenceMapTest
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class PersistenceMapTest {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = initDb();
        PersistentMap persistentMap = new PersistentMapImpl(dataSource);

        Scanner scanner = new Scanner(System.in);
        String operation;

        while (true) {
            System.out.println("init    - инициализировать persistent map ");
            System.out.println("hasKey   - проверить наличие ключа ");
            System.out.println("getKeys - получить лист ключей ");
            System.out.println("get     - найти значение по ключу ");
            System.out.println("remove  - удалить значение по ключу ");
            System.out.println("put     - поместить пару ключ, значение ");
            System.out.println("clear   - очистить persistent map ");
            System.out.println("exit    - выход");
            System.out.print("Введите операцию: ");
            operation = scanner.next();

            switch (operation) {
                case ("init") -> {
                    System.out.print("Введите имя persistent map: ");
                    String name = scanner.next();
                    persistentMap.init(name);
                }
                case ("hasKey") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();

                    System.out.print("Наличие ключа: ");
                    System.out.println(persistentMap.containsKey(key));
                }
                case ("getKeys") -> {
                    System.out.print("Лист ключей: ");
                    System.out.println(persistentMap.getKeys());
                }
                case ("get") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();

                    System.out.print("Значение по ключу: ");
                    System.out.println(persistentMap.get(key));
                }
                case ("remove") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();

                    persistentMap.remove(key);
                }
                case ("put") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();
                    System.out.print("Введите значение: ");
                    String value = scanner.next();

                    persistentMap.put(key, value);
                }
                case ("clear") -> {
                    persistentMap.clear();
                }
            }
            if (operation.equals("exit")) {
                break;
            }
          System.out.println();
        }
    }

    /**
     * Инициализация DB
     *
     * @return Datasource
     * @throws SQLException
     */
    public static DataSource initDb() throws SQLException {
        String createMapTable = ""
                + "drop table if exists persistent_map; "
                + "CREATE TABLE if not exists persistent_map (\n"
                + "   map_name varchar,\n"
                + "   KEY varchar,\n"
                + "   value varchar\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createMapTable, dataSource);
        return dataSource;
    }
}
