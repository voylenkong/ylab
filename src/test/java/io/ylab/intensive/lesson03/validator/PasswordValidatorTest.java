package io.ylab.intensive.lesson03.validator;

import io.ylab.intensive.lesson03.validator.PasswordValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TransliteratorTest
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class PasswordValidatorTest {

    @Test
    public void testValidateLoginPassword() {

        String login1 = "Super_Man";
        String password1 = "123qwE";
        String confirmPassword1 = "123qwE";
        String login2 = "Super-Man";
        String password2 = "123qwE";
        String confirmPassword2 = "123qwE";
        String login3 = "Super_Man";
        String password3 = "123qwE12345678910111213";
        String confirmPassword3 = "123qwE12345678910111213";
        String login4 = "Super_Man";
        String password4 = "123!qwE";
        String confirmPassword4 = "123!qwE";
        String login5 = "Super_Man";
        String password5 = "123qwE";
        String confirmPassword5 = "123qw";
        String login6 = "Super-Man";
        String password6 = "123qwE";
        String confirmPassword6 = "123qw";
        String login7 = "SuperMan123456789101112";
        String password7 = "123qwE";
        String confirmPassword7 = "123qw";

        assertAll(
                () -> assertTrue(PasswordValidator.validateLoginPassword(login1, password1, confirmPassword1)),
                () -> assertFalse(PasswordValidator.validateLoginPassword(login2, password2, confirmPassword2)),
                () -> assertFalse(PasswordValidator.validateLoginPassword(login3, password3, confirmPassword3)),
                () -> assertFalse(PasswordValidator.validateLoginPassword(login4, password4, confirmPassword4)),
                () -> assertFalse(PasswordValidator.validateLoginPassword(login5, password5, confirmPassword5)),
                () -> assertFalse(PasswordValidator.validateLoginPassword(login6, password6, confirmPassword6)),
                () -> assertFalse(PasswordValidator.validateLoginPassword(login7, password7, confirmPassword7))
        );
    }
}
