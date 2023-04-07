package io.ylab.intensive.lesson02.snils;

/**
 * SnilsValidator
 * <p>
 * Валидация СНИЛС.
 *
 * @author VoylenkoNG 11.03.2023
 */
public interface SnilsValidator {

    /**
     * Проверяет, что в строке содержится валиндный номер СНИЛС
     *
     * @param snils Снилс
     * @return Результат проверки
     */
    boolean validate(String snils);
}
