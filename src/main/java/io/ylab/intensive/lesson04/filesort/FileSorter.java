package io.ylab.intensive.lesson04.filesort;

import java.io.File;

/**
 * FileSorter
 * <p>
 * Сортировка файла, состоящего из чисел (long),
 * разделенных переносом строки и возвращает файл, в котором эти числа
 * отсортированы в порядке убывания.
 * 1. Можно считать, что максимальный размер файла - 1000000 чисел
 * 2. Сортировку необходимо реализовать средствами БД
 * 3. Работа с БД - средствами JDBC
 * 4. При вставке данных обязательно использовать batch-processing
 * 5. Реализовать версию без batch-processing, сравнить производительность
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public interface FileSorter {
    /**
     * Реализация с batch-processing
     *
     * @param data Входной файл для сорировки
     * @return Отсортированный файл
     */
    File sort(File data);

    /**
     * Реализация без batch-processing
     *
     * @param data Входной файл для сорировки
     * @return Отсортированный файл
     */
    File sortNotBatch(File data);
}
