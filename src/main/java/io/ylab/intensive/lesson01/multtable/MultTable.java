package io.ylab.intensive.lesson01.multtable;

/**
 * MultTable
 *
 * @author VoylenkoNG
 * 04.03.2023
 */
public class MultTable {

    /**
     * Печать таблицы умножения чисел от 1 до 9 (включая)
     */
    public static void printMultTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.println(i + " x " + j + " = " + i * j);
            }
        }
    }
}
