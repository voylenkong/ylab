package io.ylab.intensive.lesson05.sqlquerybuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Получение метаданных с БД
 * <p>
 *
 * @author VoylenkoNG
 * 01.04.2023
 */
public interface SQLQueryBuilder {

    /**
     * Получает на вход имя таблицы и выполняет следующее
     * 1. Проверяет, что данная таблица есть в БД
     * 2. Если таблицы нет - метод возвращает null
     * 3. Если таблица есть - получает список колонок
     * 4. На основании списка колонок составляется строка запроса вида “SELECT <col1>, <col2>, <col3> FROM <tablename>”
     *
     * @param tableName Имя таблицы
     * @return Строка запрос вида “SELECT <col1>, <col2>, <col3> FROM <tablename>”
     * @throws SQLException
     */
    String queryForTable(String tableName) throws SQLException;

    /**
     * Возвращает в качестве результата список имен всех таблиц, которые есть в БД
     *
     * @return Лист всех таблиц с БД
     * @throws SQLException
     */
    List<String> getTables() throws SQLException;
}
