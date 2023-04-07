package io.ylab.intensive.lesson03.validator;

import io.ylab.intensive.lesson03.validator.exception.WrongPasswordException;
import io.ylab.intensive.lesson03.validator.exception.WrongLoginException;

/**
 * PasswordValidator
 * <p>
 * Валидация логина, пароля и подтверждения пароля
 * 1. Login должен содержать только латинские буквы, цифры и знак подчеркивания.
 * Если login не соответствует - выбросить WrongLoginException с текстом “Логин
 * содержит недопустимые символы”
 * 2. Длина login должна быть меньше 20 символов. Если login не соответствует этим
 * требованиям, необходимо выбросить WrongLoginException с текстом “Логин
 * слишком длинный”
 * 3. Password должен содержать только латинские буквы, цифры и знак
 * подчеркивания. Если password не соответствует этим требованиям, необходимо
 * выбросить WrongPasswordException с текстом “Пароль содержит недопустимые
 * символы”
 * 4. Длина password должна быть меньше 20 символов. Если password не
 * соответствует этим требованиям, необходимо выбросить
 * WrongPasswordException с текстом “Пароль слишком длинный”
 * 5. Также password и confirmPassword должны быть равны. Если password не
 * соответствует этим требованиям, необходимо выбросить
 * WrongPasswordException с текстом “Пароль и подтверждение не совпадают”
 * 6. WrongPasswordException и WrongLoginException - пользовательские классы
 * исключения с двумя конструкторами – один по умолчанию, второй принимает
 * сообщение исключения и передает его в конструктор класса Exception.
 * 7. Обработка исключений проводится внутри метода. Обработка исключений -
 * вывод сообщения об ошибке консоль
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class PasswordValidator {

    /**
     * Валидация логина, пароля, подтверждения пароля
     *
     * @param login           Логин
     * @param password        Пароль
     * @param confirmPassword Подтверждение пароля
     * @return Реультат валидации
     */
    public static boolean validateLoginPassword(String login, String password, String confirmPassword) {

        boolean result = true;
        try {
            validateLogin(login);
            validatePassword(password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;

    }

    /**
     * Валидация логина
     *
     * @param login Логин
     * @throws WrongLoginException
     */
    private static void validateLogin(String login) throws WrongLoginException {
        if (!validateSymbols(login)) {
            throw new WrongLoginException("Логин содержит недопустимые символы");
        }
        if (!validateLength(login, 20)) {
            throw new WrongLoginException("Логин слишком длинный");
        }
    }

    /**
     * Валидация пароля
     *
     * @param password        Пароль
     * @param confirmPassword Подтверждение пароля
     * @throws WrongPasswordException
     */
    private static void validatePassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!validateSymbols(password)) {
            throw new WrongPasswordException("Пароль содержит недопустимые символы");
        }
        if (!validateLength(password, 20)) {
            throw new WrongPasswordException("Пароль слишком длинный");
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароль и подтверждение не совпадают");
        }
    }

    /**
     * Валидация состава символов
     *
     * @param input Входная строка
     * @return Результат
     */
    private static boolean validateSymbols(String input) {
        return input.matches("^[a-zA-Z0-9_]*$");
    }

    /**
     * Валидация длины
     *
     * @param input  Входная строка
     * @param length Допустимая длина
     * @return Результат
     */
    private static boolean validateLength(String input, int length) {
        return input.length() < length;
    }
}
