package io.ylab.intensive.lesson01.stars;

/**
 * Stars
 *
 * @author VoylenkoNG
 * 04.03.2023
 */
public class Stars {

    /**
     * Вывод фигуры, состоящей из заданного списка строк и заданного количества столбцов, и каждый элемент в которой равен указанному символу.
     *
     * @param n Высота фигуры
     * @param m Ширина фигуры
     * @param c Символ фигуры
     */
    public static void printShape(int n, int m, String c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j != m - 1) {
                    System.out.print(c + " ");
                } else {
                    System.out.println(c);
                }
            }
        }
    }
}
