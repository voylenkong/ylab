package io.ylab.intensive.lesson03.filesort.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * FileMerger
 * Слияние файлов
 *
 * @author VoylenkoNG
 * 19.03.2023
 */
public class FileMerger {

    /**
     * Слияние файлов
     *
     * @param inputFiles Список файлов для слияния
     * @return Отсортированный файл
     */
    public static File mergeFileList(List<File> inputFiles) {
        List<File> result = new ArrayList<>();
        do {
            result = combineFilesByTwo(inputFiles);
            inputFiles = result;
        } while (result.size() != 1);

        return result.get(0);
    }

    /**
     * Слияние файлов попарно
     *
     * @param inputFiles Список файлов для слияния
     * @return Новый файл слитый из двух
     */
    private static List<File> combineFilesByTwo(List<File> inputFiles) {
        List<File> mergedFiles = new ArrayList<>();
        List<File> result = new ArrayList<>();

        for (int i = 0, j = 1; j < inputFiles.size(); i += 2, j += 2) {
            result.add(FileMerger.mergeFiles(inputFiles.get(i), inputFiles.get(j)));
            mergedFiles.add(inputFiles.get(i));
            mergedFiles.add(inputFiles.get(j));
        }

        if ((inputFiles.size() % 2) != 0) {
            result.add(inputFiles.get(inputFiles.size() - 1));
        }

        for (File elem : mergedFiles) {
            if (elem.getName().startsWith("tmp") || elem.getName().startsWith("split")) {
                elem.delete();
            }
        }
        return result;
    }

    /**
     * Слияние двух файлов с учетом сортировки
     *
     * @param inputFile1 Файл 1
     * @param inputFile2 Файл 2
     * @return Слитый файл из 2 с учетом сортировки
     */
    public static File mergeFiles(File inputFile1, File inputFile2) {
        File fileOutputResult = new File(inputFile1.getAbsoluteFile().getParent() + "/tmp" + UUID.randomUUID() + ".txt");
        try (
                BufferedReader br1 = new BufferedReader(new FileReader(inputFile1));
                BufferedReader br2 = new BufferedReader(new FileReader(inputFile2));

                BufferedWriter bwResult = new BufferedWriter(new FileWriter(fileOutputResult));
        ) {
            String line1 = br1.readLine();
            String line2 = br2.readLine();
            if (Objects.isNull(line1) & Objects.isNull(line2)) {
                return fileOutputResult;
            }

            long elem1 = 0;
            long elem2 = 0;

            while (Objects.nonNull(line1) & Objects.nonNull(line2)) {

                elem1 = Long.parseLong(line1);
                elem2 = Long.parseLong(line2);

                if (elem1 < elem2) {
                    writeMinElem(line1, bwResult);
                    line1 = br1.readLine();
                }
                if (elem1 > elem2) {
                    writeMinElem(line2, bwResult);
                    line2 = br2.readLine();
                }
                if (elem1 == elem2) {
                    writeMinElem(line1, bwResult);
                    writeMinElem(line2, bwResult);
                    line1 = br1.readLine();
                    line2 = br2.readLine();
                }
            }

            if (Objects.nonNull(line1) & Objects.isNull(line2)) {
                appendTailToResult(line1, br1, bwResult);
            }
            if (Objects.isNull(line1) & Objects.nonNull(line2)) {
                appendTailToResult(line2, br2, bwResult);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return fileOutputResult;
    }

    /**
     * Запись значения в файл
     *
     * @param line     Строка для записи
     * @param bwResult BufferedWriter
     * @throws IOException
     */
    private static void writeMinElem(String line, BufferedWriter bwResult) throws IOException {
        bwResult.write(line);
        bwResult.newLine();
    }

    /**
     * Добавление конца одного из двух файлов, если втрой дочитан до конца
     *
     * @param line     Текущая строка
     * @param br       BufferedReader
     * @param bwResult BufferedWriter
     * @throws IOException
     */
    private static void appendTailToResult(String line, BufferedReader br, BufferedWriter bwResult) throws IOException {
        while (Objects.nonNull(line)) {
            bwResult.write(line);
            bwResult.newLine();
            line = br.readLine();
        }
    }
}
