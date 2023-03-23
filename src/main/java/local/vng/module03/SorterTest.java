package local.vng.module03;

import local.vng.module03.filesort.Sorter;
import local.vng.module03.filesort.util.Generator;
import local.vng.module03.filesort.util.Validator;

import java.io.File;
import java.io.IOException;

/**
 * SorterTest
 *
 * @author VoylenkoNG
 * 19.03.2023
 */
public class SorterTest {
    public static void main(String[] args) {
        var sorter = new Sorter();

        try {
            String fileName = "src\\main\\resources\\data.txt";

            //File dataFile = new Generator().generate(fileName, 375_000_000);
            File dataFile = new Generator().generate(fileName, 1_100_000);

            System.out.print("Исходный файл отсортирован? ");
            System.out.println(new Validator(dataFile).isSorted());

            System.out.print("Полученный файл отсортирован? ");
            File sortedDataFile = sorter.sortFile(dataFile);
            System.out.println(new Validator(sortedDataFile).isSorted());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
