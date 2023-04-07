package io.ylab.intensive.lesson04.persistentmap;

import java.sql.SQLException;
import java.util.List;

/**
 * PersistentMap
 * <p>
 * Реализация Map, хранящая свое состояние исключительно в базе
 * данных. То есть, любое изменение данных Map (добавление и удаление), а также
 * получение данных транслируются в соответствующие SQL запросы.
 * Допущение: можно считать, что одновременно только одно приложение будет
 * работать с конкретным экземпляром. То есть, соблюдение строгой транзакционности и
 * реализация многопоточной работы не обязательны!
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public interface PersistentMap {

    /**
     * Инициализации нового экземпляра Map. Принимает имя
     * текущего экземпляра. Данные всех экземпляров хранятся в одной таблице, и имя
     * используется для того, чтобы отделять данные одного экземпляра от данных другого
     *
     * @param name Имя текущего экземпляра
     */
    void init(String name);

    /**
     * Возвращает true тогда и только тогда, когда существует значение,
     * связанное с данным ключом, false - в противном случае
     *
     * @param key Ключ
     * @return Результат проверки
     * @throws SQLException
     */
    boolean containsKey(String key) throws SQLException;

    /**
     * Возвращает список ключей, для которых есть значения в БД
     *
     * @return Лист ключей
     * @throws SQLException
     */
    List<String> getKeys() throws SQLException;

    /**
     * Возвращает значение, связанное с переданным ключом
     *
     * @param key Ключ
     * @return Значение
     * @throws SQLException
     */
    String get(String key) throws SQLException;

    /**
     * Удаляет пару ключ/значение из Map
     *
     * @param key Ключ
     * @throws SQLException
     */
    void remove(String key) throws SQLException;

    /**
     * Служит для добавления новой пары ключ-значение. В своей работе сначала
     * удаляет существую пару из Map (если она есть), а затем добавляет новую
     *
     * @param key   Ключ
     * @param value Значение
     * @throws SQLException
     */
    void put(String key, String value) throws SQLException;

    /**
     * Удаляет все данные из текущего экземпляра Map
     *
     * @throws SQLException
     */
    void clear() throws SQLException;
}
