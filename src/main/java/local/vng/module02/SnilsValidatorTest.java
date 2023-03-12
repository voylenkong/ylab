package local.vng.module02;

import java.util.Scanner;

import local.vng.module02.snils.SnilsValidatorImpl;

/**
 * SnilsValidatorTest
 * <p>
 * Валидация СНИЛС.
 *
 * @author VoylenkoNG
 * 11.03.2023
 */
public class SnilsValidatorTest {
    public static void main(String[] args) {
        var snilsValidator = new SnilsValidatorImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите СНИЛС для проверки: ");
        String inputSnils = scanner.next();

        if (snilsValidator.validate(inputSnils)) {
            System.out.println("СНИЛС валиден.");
        } else {
            System.out.println("СНИЛС не валиден.");
        }

        scanner.close();
    }
}
