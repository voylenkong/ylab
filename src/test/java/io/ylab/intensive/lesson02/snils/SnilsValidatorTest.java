package io.ylab.intensive.lesson02.snils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SnilsValidatorTest
 *
 * @author VoylenkoNG
 * 12.03.2023
 */
public class SnilsValidatorTest {

  @Test
  public void testValidate() {
    var snilsValidator = new SnilsValidatorImpl();

    String snils1 = "1253gfgh565";
    String snils2 = "12345";
    String snils3 = "9011440444156";
    String snils4 = "12345678901";
    String snils5 = "90114404441";
    String snils6 = "13741249553";

    assertAll(
        () -> assertFalse(snilsValidator.validate(snils1)),
        () -> assertFalse(snilsValidator.validate(snils2)),
        () -> assertFalse(snilsValidator.validate(snils3)),
        () -> assertFalse(snilsValidator.validate(snils4)),
        () -> assertTrue(snilsValidator.validate(snils5)),
        () -> assertTrue(snilsValidator.validate(snils6))
    );
  }
}
