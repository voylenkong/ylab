package local.vng.lesson05.eventsourcing.api;

import java.util.List;

import local.vng.lesson05.eventsourcing.Person;

/**
 * PersonApi
 * <p>
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public interface PersonApi {
    /**
     * Генерирует сообщение-команду на удаление персоны с заданным
     * id. Далее это сообщение должно быть обработано соответствующим запросом,
     * выполняя удаление данных. Если данных для определенного personId не
     * найдено - выводить в лог сообщение, что была попытка удаления, но при этом
     * данные не найдены. Exception или другую ошибку не выдавать
     *
     * @param personId id
     */
    void deletePerson(Long personId);

    /**
     * Генерирует сообщение-команду на сохранение данных персоны.
     * Обработчик должен проверить, существует ли в БД персона с переданным
     * personId. Если существует - необходимо выполнить обновление данных
     * (обновить три поля firstName, lastName, middleName). Если не существует -
     * создать персону с переданным personId
     *
     * @param personId   id
     * @param firstName  Фамилия
     * @param lastName   Имя
     * @param middleName Отчетсво
     */
    void savePerson(Long personId, String firstName, String lastName, String middleName);

    /**
     * Генерирует запрос напрямую в БД и возвращает данные персоны,
     * если персона для данного personId найдена, null в противном случае
     *
     * @param personId id
     * @return Person {@link Person}
     */
    Person findPerson(Long personId);

    /**
     * Генерирует запрос напрямую в БД и возвращает данные о ВСЕХ
     * сохраненных в базе персонах
     *
     * @return Лист Person {@link Person}
     */
    List<Person> findAll();
}
