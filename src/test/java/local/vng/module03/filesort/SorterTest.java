package local.vng.module03.filesort;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SorterTest
 *
 * @author VoylenkoNG
 * 19.03.2023
 */
public class SorterTest {

    @BeforeEach
    public void init() {
        var sorter = new Sorter();
        File dataTestFile = new File("src\\main\\resources\\dataTest.txt");

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(dataTestFile));
        ) {
            long[] initData = new long[]{234, -90, 500, 0, 33, 33, 3};
            for (long elem : initData) {
                bw.write(String.valueOf(elem));
                bw.newLine();
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        try {
            sorter.sortFile(dataTestFile);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    @AfterEach
    public void end() {
        File dataTestFile = new File("src\\main\\resources\\dataTest.txt");
        File sortedDataTestFile = new File("src\\main\\resources\\dataTestSorted.txt");

        dataTestFile.delete();
        sortedDataTestFile.delete();
    }

    @Test
    public void testSort() {
        File sortedDataTestFile = new File("src\\main\\resources\\dataTestSorted.txt");

        try (
                BufferedReader br = new BufferedReader(new FileReader(sortedDataTestFile));
        ) {

            var sortedReadData = new ArrayList<Long>();
            String line = br.readLine();
            while (Objects.nonNull(line)) {
                sortedReadData.add(Long.valueOf(line));
                line = br.readLine();
            }

            assertAll(
                    () -> assertEquals(-90, sortedReadData.get(0)),
                    () -> assertEquals(0, sortedReadData.get(1)),
                    () -> assertEquals(3, sortedReadData.get(2)),
                    () -> assertEquals(33, sortedReadData.get(3)),
                    () -> assertEquals(33, sortedReadData.get(4)),
                    () -> assertEquals(234, sortedReadData.get(5)),
                    () -> assertEquals(500, sortedReadData.get(6))
            );
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
