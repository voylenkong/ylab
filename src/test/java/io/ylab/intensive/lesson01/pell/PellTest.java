package io.ylab.intensive.lesson01.pell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PellTest
 *
 * @author VoylenkoNG
 * 05.03.2023
 */
public class PellTest {

    @Test
    public void testPell() {

        assertAll(
                () -> assertEquals(0, Pell.getPell(0)),
                () -> assertEquals(1, Pell.getPell(1)),
                () -> assertEquals(29, Pell.getPell(5)),
                () -> assertEquals(70, Pell.getPell(6))
        );

    }
}
