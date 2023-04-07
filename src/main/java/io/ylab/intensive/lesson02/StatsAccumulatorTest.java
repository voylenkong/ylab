package io.ylab.intensive.lesson02;

import java.util.Scanner;

import io.ylab.intensive.lesson02.accumulator.StatsAccumulator;
import io.ylab.intensive.lesson02.accumulator.StatsAccumulatorImpl;

/**
 * StatsAccumulatorTest
 * <p>
 * Аккумулятор позволяет принять число.
 * Возвращает минимальное число, максимальное число, количество переданных чисел, среднее арифметическое число.
 *
 * @author VoylenkoNG
 * 11.03.2023
 */
public class StatsAccumulatorTest {
    public static void main(String[] args) {
        StatsAccumulator statsAccumulator = new StatsAccumulatorImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Допустимые операции с StatsAccumulator (add, min, max, count, avg):");
        System.out.println("add - добавить число");
        System.out.println("min - получить минимальное число из ранее введенных");
        System.out.println("max - получить максимальное число из ранее введенных");
        System.out.println("count - получить количество всех ранее введенных чисел");
        System.out.println("avg - получить среднее число из всех ранее введенных чисел");
        System.out.println("exit - выход");

        String operation;

        while (true) {
            System.out.print("Введите операцию: ");
            operation = scanner.next();

            switch (operation) {
                case ("add") -> {
                    System.out.print("Введите число: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Ошибка. Введи число: ");
                        scanner.next();
                    }
                    statsAccumulator.add(Integer.parseInt(scanner.next()));
                }
                case ("min") -> {
                    System.out.println("Минимальное число: " + statsAccumulator.getMin());
                }
                case ("max") -> {
                    System.out.println("Максимальное число: " + statsAccumulator.getMax());
                }
                case ("count") -> {
                    System.out.println("Количество чисел: " + statsAccumulator.getCount());
                }
                case ("avg") -> {
                    System.out.println("Среднее число: " + statsAccumulator.getAvg());
                }
            }

            if (operation.equals("exit")) {
                break;
            }
        }

        scanner.close();
    }
}
