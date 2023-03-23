package local.vng.module03;

import local.vng.module03.validator.PasswordValidator;

import java.util.Scanner;

/**
 * PassordValidatorTest
 * <p>
 * Валидация логина, пароля, подтверждения пароля
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class PasswordValidatorTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String login = scanner.next();

        System.out.print("Введите пароль: ");
        String password = scanner.next();

        System.out.print("Подтвердите пароль: ");
        String confirmPassword = scanner.next();

        if (PasswordValidator.validateLoginPassword(login, password, confirmPassword)) {
            System.out.println("Пароль и логин валидированы");
        }

        scanner.close();
    }
}
