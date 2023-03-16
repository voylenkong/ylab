package local.vng.module02;

import java.util.Scanner;

import local.vng.module02.complex.ComplexNumber;

/**
 * ComplexNumberTest
 * <p>
 * Операции с комплексными числами: сложение, вычитание, умножение, модуль числа
 *
 * @author VoylenkoNG
 * 11.03.2023
 */
public class ComplexNumberTest {
    public static void main(String[] args) {
        var a = new ComplexNumber();
        var b = new ComplexNumber();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите операцию над комплексным числом (add, sub, mul, abs):");
        System.out.println("add - сложение двух комплексных чисел");
        System.out.println("sub - разность двух комплексных чисел");
        System.out.println("mul - произведение двух комплексных чисел");
        System.out.println("abs - модуль комплексного числа");
        System.out.print("Введите операцию: ");
        String operation = scanner.next();

        if (operation.equals("abs")) {
            a = inputComplexNumber(scanner);

        } else if (operation.equals("add") || operation.equals("sub") || operation.equals("mul")) {
            System.out.println("Число a: ");
            a = inputComplexNumber(scanner);

            System.out.println("Число b: ");
            b = inputComplexNumber(scanner);

        } else {
            System.out.println("Неверная операция!");
        }

        switch (operation) {
            case ("add") -> {
                System.out.println("Сумма двух комплексных чисел a + b = " + a.add(b));
            }
            case ("sub") -> {
                System.out.println("Разность двух комплексных чисел a - b = " + a.sub(b));
            }
            case ("mul") -> {
                System.out.println("Произведение двух комплексных чисел a * b = " + a.mul(b));
            }
            case ("abs") -> {
                System.out.println("Модуль комплексного числа a = " + a.abs());
            }
        }

        scanner.close();
    }

    private static ComplexNumber inputComplexNumber(Scanner scanner) {
        System.out.print("Введи действительную часть числа: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка. Введи число: ");
            scanner.next();
        }
        int reA = Integer.parseInt(scanner.next());

        System.out.print("Введи мнимую часть числа: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка. Введи число: ");
            scanner.next();
        }
        int imA = Integer.parseInt(scanner.next());

        return new ComplexNumber(reA, imA);
    }
}
