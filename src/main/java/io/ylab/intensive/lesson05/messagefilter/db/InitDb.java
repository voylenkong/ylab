package io.ylab.intensive.lesson05.messagefilter.db;

import java.io.File;

/**
 * InitDb
 *
 * @author VoylenkoNG
 * 02.04.2023
 */
public interface InitDb {

    /**
     * Инициализация БД со списком нецензурных слов из файла
     *
     * @param file Файл со списком нецензурных слов
     */
    void initDb(File file);
}
