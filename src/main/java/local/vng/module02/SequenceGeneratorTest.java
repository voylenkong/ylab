package local.vng.module02;

import java.util.Scanner;

import local.vng.module02.sequence.SequenceGenerator;
import local.vng.module02.sequence.SequenceGeneratorImpl;

/**
 * SequenceGeneratorTest
 * <p>
 * Генерация последовательностей A - J:
 * A: Последовательность четных чисел
 * B: Последовательность нечетных чисел
 * C: Квадратичная последовательность
 * D: Кубическая последовательность
 * E: Последовательность положительных и отрицательных 1
 * F: Линейная последовательность нечетное положительное, четное отрицательное
 * G: Квадратичная последовательность нечетное положительное, четное отрицательное
 * H: Нечетное число 0, четное - линейная последовательность
 * I: Последовательность факторала чисел
 * J: Последовательность Фибоначчи
 *
 * @author VoylenkoNG
 * 11.03.2023
 */
public class SequenceGeneratorTest {
    public static void main(String[] args) {
        SequenceGenerator sequenceGenerator = new SequenceGeneratorImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите длину построения последовательностей A-J: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка. Введи число: ");
            scanner.next();
        }
        int n = Integer.parseInt(scanner.next());

        System.out.println("Последовательность четных чисел");
        System.out.print("A. ");
        sequenceGenerator.a(n);
        System.out.println();
        System.out.println();

        System.out.println("Последовательность нечетных чисел");
        System.out.print("B. ");
        sequenceGenerator.b(n);
        System.out.println();
        System.out.println();

        System.out.println("Квадратичная последовательность");
        System.out.print("C. ");
        sequenceGenerator.c(n);
        System.out.println();
        System.out.println();

        System.out.println("Кубическая последовательность");
        System.out.print("D. ");
        sequenceGenerator.d(n);
        System.out.println();
        System.out.println();

        System.out.println("Последовательность положительных и отрицательных 1");
        System.out.print("E. ");
        sequenceGenerator.e(n);
        System.out.println();
        System.out.println();

        System.out.println("Линейная последовательность нечетное положительное, четное отрицательное");
        System.out.print("F. ");
        sequenceGenerator.f(n);
        System.out.println();
        System.out.println();

        System.out.println("Квадратичная последовательность нечетное положительное, четное отрицательное");
        System.out.print("G. ");
        sequenceGenerator.g(n);
        System.out.println();
        System.out.println();

        System.out.println("Нечетное число 0, четное - линейная последовательность");
        System.out.print("H. ");
        sequenceGenerator.h(n);
        System.out.println();
        System.out.println();

        System.out.println("Последовательность факторала чисел");
        System.out.print("I. ");
        sequenceGenerator.i(n);
        System.out.println();
        System.out.println();

        System.out.println("Последовательность Фибоначчи");
        System.out.print("J. ");
        sequenceGenerator.j(n);
        System.out.println();
        System.out.println();

        scanner.close();
    }
}
