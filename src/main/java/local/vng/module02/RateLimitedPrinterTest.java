package local.vng.module02;

import java.util.Scanner;

import local.vng.module02.printer.RateLimitedPrinterImpl;

/**
 * RateLimitedPrinterTest
 * <p>
 * Ограничение вывода в консоль сообщения не чаще чем заданный интервал.
 *
 * @author VoylenkoNG
 * 11.03.2023
 */
public class RateLimitedPrinterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи интервал в милисекундах: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка. Введи число: ");
            scanner.next();
        }
        int interval = scanner.nextInt();

        var rateLimitedPrinter = new RateLimitedPrinterImpl(interval);

        for (int i = 0; i < 1_000_000_000; i++) {
            rateLimitedPrinter.print(String.valueOf(i));
        }

        scanner.close();
    }

}
