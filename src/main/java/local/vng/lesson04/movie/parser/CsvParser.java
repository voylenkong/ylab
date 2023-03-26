package local.vng.lesson04.movie.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * CsvParser
 * <p>
 * Парсинг файла с заголовками (csv)
 *
 * @author VoylenkoNG
 * 20.03.2023
 */
public class CsvParser {

    /**
     * Парсинг файла с заголовками (csv)
     *
     * @param inputFile Входной файл с заголовками (csv)
     * @param delimiter Разделитель данных
     * @return Список записей имя столбца, значение
     */
    public List<Map<String, String>> parseCsv(File inputFile, String delimiter) {

        List<Map<String, String>> result = new ArrayList<>();

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
        ) {
            String line = br.readLine();
            Map<Integer, String> headerMap = new HashMap<>();

            String[] headerArr = line.split(delimiter);
            for (int i = 0; i < headerArr.length; i++) {
                headerMap.put(i, headerArr[i]);
            }

            /**
             * Пропуск двух строк
             * 1-я заголовок
             * 2-я тип данных
             */
            line = br.readLine();
            line = br.readLine();
            while (nonNull(line)) {
                String[] recordArr = line.split(delimiter);
                Map<String, String> recordMap = new HashMap<>();

                for (int i = 0; i < recordArr.length; i++) {
                    recordMap.put(headerMap.get(i), recordArr[i]);
                }

                result.add(recordMap);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
