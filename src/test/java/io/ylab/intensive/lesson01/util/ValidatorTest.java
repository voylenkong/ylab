package io.ylab.intensive.lesson01.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ValidatorTest
 *
 * @author VoylenkoNG
 * 05.03.2023
 */
public class ValidatorTest {

    @Test
    public void testIsValidNumber() {

        assertAll(
                () -> assertTrue(Validator.isValidNumber("1235")),
                () -> assertTrue(Validator.isValidNumber("456")),
                () -> assertFalse(Validator.isValidNumber("-12")),
                () -> assertFalse(Validator.isValidNumber("1hj")),
                () -> assertFalse(Validator.isValidNumber("hj"))
        );
    }

    @Test
    public void testIsValidSize() {

        assertAll(
                () -> assertTrue(Validator.isValidSize("1")),
                () -> assertTrue(Validator.isValidSize("$")),
                () -> assertFalse(Validator.isValidSize("hj"))
        );
    }
}
