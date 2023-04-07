package io.ylab.intensive.lesson04.movie;

import java.io.File;

/**
 * MovieLoader
 * <p>
 * Загрузка данных (БД фильмов) из файла CSV файла и записывающий в таблицу через
 * JDBC. Для добавления данных использовать PreparedStatement.
 * В работе необходимо использовать класс Movie {@link Movie}
 * Данные, считываемые из файла должны быть упакованы в экземпляр
 * указанного класса.
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public interface MovieLoader {
    /**
     * Загрзка данных из файла CSV в БД
     *
     * @param file Входной файл csv
     */
    void loadData(File file);
}
