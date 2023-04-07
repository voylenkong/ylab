package io.ylab.intensive.lesson03.filesort.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Validator
 * Валидация - является ли файл отсортированным по возрастанию
 *
 * @author YLab
 * 19.03.2023
 */
public class Validator {
    private File file;

    public Validator(File file) {
        this.file = file;
    }

    /**
     * Валидация - является ли файл отсортированным по возрастанию
     *
     * @return Результат валидации
     */
    public boolean isSorted() {
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            long prev = Long.MIN_VALUE;
            while (scanner.hasNextLong()) {
                long current = scanner.nextLong();
                if (current < prev) {
                    return false;
                } else {
                    prev = current;
                }
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
