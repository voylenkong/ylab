package local.vng.lesson04.persistentmap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import static java.util.Objects.isNull;

/**
 * PersistentMapImpl
 * <p>
 * Имплементация {@link PersistentMap}
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class PersistentMapImpl implements PersistentMap {
    private DataSource dataSource;

    private String name;

    private static final String SELECT_KEY_COUNT = "SELECT COUNT(KEY) FROM persistent_map WHERE (map_name = ?) AND (KEY = ?)";
    private static final String INSERT_DATA = "INSERT INTO persistent_map (map_name, key, value) VALUES(?, ?, ?)";
    private static final String DELETE_DATA_BY_KEY = "DELETE FROM persistent_map WHERE (map_name = ?) AND (key = ?)";
    private static final String DELETE_ALL_DATA = "DELETE FROM persistent_map WHERE map_name = ?";
    private static final String SELECT_DATA_BY_KEY = "SELECT value FROM persistent_map WHERE (map_name = ?) AND (key = ?)";
    private static final String SELECT_KEY_LIST = "SELECT key FROM persistent_map WHERE map_name = ?";

    public PersistentMapImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void init(String name) {
        this.name = name;
    }

    @Override
    public boolean containsKey(String key) throws SQLException {
        if (isNull(this.name)) {
            System.out.println("PersistentMap не инициализирована. Выполните инициализацию");
        } else {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(SELECT_KEY_COUNT);
            ) {
                ps.setString(1, this.name);
                ps.setString(2, key);
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                return resultSet.getInt(1) != 0;
            }
        }
        return false;
    }

    @Override
    public List<String> getKeys() throws SQLException {
        List<String> result = new ArrayList<>();
        if (isNull(this.name)) {
          System.out.println("PersistentMap не инициализирована. Выполните инициализацию");
        } else {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(SELECT_KEY_LIST);
            ) {
                ps.setString(1, this.name);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    result.add(resultSet.getString(1));
                }
            }
        }
        return result;
    }

    @Override
    public String get(String key) throws SQLException {
        if (isNull(this.name)) {
          System.out.println("PersistentMap не инициализирована. Выполните инициализацию");
        } else {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(SELECT_DATA_BY_KEY);
            ) {
                if (this.containsKey(key)) {
                    ps.setString(1, this.name);
                    ps.setString(2, key);
                    ResultSet resultSet = ps.executeQuery();
                    resultSet.next();
                    return resultSet.getString(1);
                }
            }
        }
        return null;
    }

    @Override
    public void remove(String key) throws SQLException {
        if (isNull(this.name)) {
          System.out.println("PersistentMap не инициализирована. Выполните инициализацию");
        } else {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(DELETE_DATA_BY_KEY);
            ) {
                if (this.containsKey(key)) {
                    ps.setString(1, this.name);
                    ps.setString(2, key);
                    ps.execute();
                }
            }
        }
    }

    @Override
    public void put(String key, String value) throws SQLException {
        if (isNull(this.name)) {
          System.out.println("PersistentMap не инициализирована. Выполните инициализацию");
        } else {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement psInsert = connection.prepareStatement(INSERT_DATA);
            ) {
                if (this.containsKey(key)) {
                    this.remove(key);
                }
                psInsert.setString(1, this.name);
                psInsert.setString(2, key);
                psInsert.setString(3, value);
                psInsert.execute();
            }
        }
    }

    @Override
    public void clear() throws SQLException {
        if (isNull(this.name)) {
          System.out.println("PersistentMap не инициализирована. Выполните инициализацию");
        } else {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(DELETE_ALL_DATA);
            ) {
                ps.setString(1, this.name);
                ps.execute();
            }
        }
    }
}
