package io.ylab.intensive.lesson03.filesort.file;

import java.io.*;
import java.util.*;

/**
 * FileSplitter
 * Разделение исходного файла на фрагменты и сортировка фрагментов
 *
 * @author VoylenkoNG
 * 19.03.2023
 */
public class FileSortSplitter {

    /**
     * Разделение исходного файла на фрагменты и сортировка фрагментов
     *
     * @param inputFile     Входной файл
     * @param maxBufferSize Размер буфера
     * @return Список разделенных файлов с исходного файла
     */
    public static List<File> splitBigFile(File inputFile, long maxBufferSize) {
        List<File> result = new ArrayList<>();
        String dir = inputFile.getAbsoluteFile().getParent();

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
        ) {
            String line = br.readLine();
            List<Long> buffer = new ArrayList<>();

            while (Objects.nonNull(line)) {
                if (buffer.size() >= maxBufferSize) {
                    sortAndFlush(result, dir, buffer);
                }
                buffer.add(Long.valueOf(line));
                line = br.readLine();
            }

            sortAndFlush(result, dir, buffer);

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return result;
    }

    /**
     * Сортировка данных в буфере, запись их в файл, очистка буфера
     *
     * @param result Полученный файл - фрагмент отсортированных данных
     * @param dir    Директория с файлами
     * @param buffer Буфер - фрагмент данных
     */
    private static void sortAndFlush(List<File> result, String dir, List<Long> buffer) {
        buffer.sort(Comparator.naturalOrder());
        result.add(writeToSplitFile(dir, buffer));
        buffer.clear();
    }

    /**
     * Формирование и запись фрагмента исходного файла
     *
     * @param dir       Расположение сохранения фрагмента
     * @param inputList Отсортированный фрагмент исходного файла
     * @return Файл - отсортированный фрагмент исходного файла
     */
    private static File writeToSplitFile(String dir, List<Long> inputList) {
        File fileOutputResult = new File(dir + "/split" + UUID.randomUUID() + ".txt");

        try (
                FileWriter fwResult = new FileWriter(fileOutputResult);
                BufferedWriter bwResult = new BufferedWriter(fwResult);
        ) {
            for (Long elem : inputList) {
                bwResult.write(elem.toString());
                bwResult.newLine();
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return fileOutputResult;
    }
}
