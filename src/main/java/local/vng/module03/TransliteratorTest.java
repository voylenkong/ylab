package local.vng.module03;

import local.vng.module03.transliterator.TransliteratorImpl;

import java.util.Scanner;

/**
 * TransliteratorTest
 * <p>
 * Транслитерация входной строки
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class TransliteratorTest {
    public static void main(String[] args) {
        var transliterator = new TransliteratorImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку для транслитерации: ");
        String input = scanner.nextLine();

        System.out.print("Транслитерированная строка: ");
        System.out.println(transliterator.transliterate(input));

        scanner.close();
    }
}
