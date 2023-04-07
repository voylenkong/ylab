package io.ylab.intensive.lesson01.util;

/**
 * Validator
 *
 * @author VoylenkoNG
 * 04.03.2023
 */
public class Validator {
    /**
     * Валидация входной строки на условие только цифры
     *
     * @param   in  Входная строка для валидации
     * @return  Результат условия на состав входной строки
     */
    public static boolean isValidNumber(String in) {
        return in.matches("^[0-9]*$");
    }

    /**
     * Валидация входной строки на 1 символ
     *
     * @param   in  Входная строка для валидации
     * @return  Результат условия на состав входной строки
     */
    public static boolean isValidSize(String in) {
        return in.length() == 1;
    }

}
