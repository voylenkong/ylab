package local.vng.lesson05.eventsourcing.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DbService
 * Сервис работы с БД со стороны приложения DbApp
 *
 * @author VoylenkoNG
 * 26.03.2023
 */
@Component
public class DbService {

    private DataSource dataSource;
    private static final String DELETE_PERSON_BY_ID = "DELETE FROM person WHERE person_id = ?";
    private static final String INSERT_PERSON = "INSERT INTO person (person_id, first_name, last_name, middle_name) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_PERSON_BY_ID = "UPDATE person SET first_name = ?, last_name = ?, middle_name = ? WHERE person_id = ?";
    private static final String SELECT_PERSON_BY_ID = "SELECT * FROM person WHERE person_id = ?";

    @Autowired
    public DbService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Удаление персоны с заданным id. Если данных для определенного personId не
     * найдено - выводить в лог сообщение, что была попытка удаления, но при этом
     * данные не найдены. Exception или другую ошибку не выдавать
     *
     * @param personId
     */
    public void deletePerson(Long personId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement psSelect = connection.prepareStatement(SELECT_PERSON_BY_ID);
             PreparedStatement psDelete = connection.prepareStatement(DELETE_PERSON_BY_ID);
        ) {
            psSelect.setLong(1, personId);
            ResultSet resultSet = psSelect.executeQuery();
            if (resultSet.next()) {
                psDelete.setLong(1, personId);
                psDelete.execute();
            } else {
                System.out.println("Пользователь с id " + personId + " не существует");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Сохранение данных персоны.
     * Должна быть проверка, существует ли в БД персона с переданным
     * personId. Если существует - необходимо выполнить обновление данных
     * (обновить три поля firstName, lastName, middleName). Если не существует -
     * создать персону с переданным personId
     *
     * @param personId   id
     * @param firstName  Фамилия
     * @param lastName   Имя
     * @param middleName Отчетсво
     */
    public void savePerson(Long personId, String firstName, String lastName, String middleName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement psSelect = connection.prepareStatement(SELECT_PERSON_BY_ID);
             PreparedStatement psInsert = connection.prepareStatement(INSERT_PERSON);
             PreparedStatement psUpdate = connection.prepareStatement(UPDATE_PERSON_BY_ID);
        ) {
            psSelect.setLong(1, personId);
            ResultSet resultSet = psSelect.executeQuery();
            if (resultSet.next()) {
                psUpdate.setString(1, firstName);
                psUpdate.setString(2, lastName);
                psUpdate.setString(3, middleName);
                psUpdate.setLong(4, personId);
                psUpdate.execute();
            } else {
                psInsert.setLong(1, personId);
                psInsert.setString(2, firstName);
                psInsert.setString(3, lastName);
                psInsert.setString(4, middleName);
                psInsert.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
