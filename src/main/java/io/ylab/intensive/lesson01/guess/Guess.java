package io.ylab.intensive.lesson01.guess;

import java.util.Random;
import java.util.Scanner;

/**
 * Guess
 *
 * @author VoylenkoNG
 * 04.03.2023
 */
public class Guess {

    /**
     * Игра угадайка.
     */
    public static void guessNumber() {
        int number = new Random().nextInt(99) + 1;
        int leftAttempts = 10;
        System.out.println("Я загадал число от 1 до 99. У тебя " + leftAttempts + " попыток угадать.");
        Scanner scanner = new Scanner(System.in);

        for (int i = 1; leftAttempts >= 1; leftAttempts--, i++) {
            System.out.print("Введи число: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Ошибка. Введи число: ");
                scanner.next();
            }

            int a = scanner.nextInt();
            if (a == number) {
                System.out.println("Ты угадал с " + i + " попытки!");
                break;
            }
            if (a > number) {
                System.out.print("Мое число меньше! ");
            }
            if (a < number) {
                System.out.print("Мое число больше! ");
            }
            System.out.println(getLeftAttemptsMsg(leftAttempts - 1));
        }
        if (leftAttempts == 0) {
            System.out.println("Ты не угадал");
        }
    }

    /**
     * Вывод сообщения:  У тебя осталось N попыток
     *
     * @param   attempts    Текущее количество попыток
     * @return  Вывод сообщения:  У тебя осталось N попыток
     */
    private static String getLeftAttemptsMsg(int attempts) {
        String attemptStr;
        if (attempts == 1) {
            attemptStr = " попытка";
        } else if ((attempts >= 2) & (attempts <= 4)) {
            attemptStr = " попытки";
        } else {
            attemptStr = " попыток";
        }
        return ("У тебя осталось " + attempts + attemptStr);
    }
}
