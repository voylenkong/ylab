package local.vng.module03.filesort;

import local.vng.module03.filesort.file.FileMerger;
import local.vng.module03.filesort.file.FileSortSplitter;

import java.io.*;
import java.util.List;

/**
 * Sorter
 * Получает на вход файл с числами, и возвращает
 * отсортированный по возрастанию файл методом внешней сортировки слиянием
 *
 * @author VoylenkoNG
 * 19.03.2023
 */
public class Sorter {
    /**
     * Получает на вход файл с числами, и возвращает
     * отсортированный по возрастанию файл методом внешней сортировки слиянием
     *
     * @param dataFile Входящий файл с неотсортированными числами
     * @return Отсортированный по возрастанию файл
     * @throws IOException
     */
    public File sortFile(File dataFile) throws IOException {
        /**
         * Размер фрагмента для сортировки. Выбирается на основании свободной памяти
         */
        long buffer = Runtime.getRuntime().freeMemory() / 16;

        List<File> splitFiles = FileSortSplitter.splitBigFile(dataFile, buffer);
        File result = FileMerger.mergeFileList(splitFiles);

        String fileName = dataFile.getName().substring(0, dataFile.getName().lastIndexOf('.'));
        File sortedFile = new File(dataFile.getAbsoluteFile().getParent() + "\\" + fileName + "Sorted.txt");
        result.renameTo(sortedFile);

        return sortedFile;
    }

}
