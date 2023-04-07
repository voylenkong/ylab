package io.ylab.intensive.lesson02.accumulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * StatsAccumulatorTest
 *
 * @author VoylenkoNG
 * 12.03.2023
 */
public class StatsAccumulatorTest {

  @Test
  public void testAdd1Elem() {
    StatsAccumulator statsAccumulator = new StatsAccumulatorImpl();
    statsAccumulator.add(2);

    assertAll(
        () -> assertEquals(2, statsAccumulator.getMin()),
        () -> assertEquals(2, statsAccumulator.getMax()),
        () -> assertEquals(1, statsAccumulator.getCount()),
        () -> assertEquals(2, statsAccumulator.getAvg())
    );
  }

  @Test
  public void testAdd2Elem() {
    StatsAccumulator statsAccumulator = new StatsAccumulatorImpl();
    statsAccumulator.add(2);
    statsAccumulator.add(6);

    assertAll(
        () -> assertEquals(2, statsAccumulator.getMin()),
        () -> assertEquals(6, statsAccumulator.getMax()),
        () -> assertEquals(2, statsAccumulator.getCount()),
        () -> assertEquals(4, statsAccumulator.getAvg())
    );
  }

  @Test
  public void testAdd3Elem() {
    StatsAccumulator statsAccumulator = new StatsAccumulatorImpl();
    statsAccumulator.add(2);
    statsAccumulator.add(6);
    statsAccumulator.add(-2);

    assertAll(
        () -> assertEquals(-2, statsAccumulator.getMin()),
        () -> assertEquals(6, statsAccumulator.getMax()),
        () -> assertEquals(3, statsAccumulator.getCount()),
        () -> assertEquals(2, statsAccumulator.getAvg())
    );
  }

  @Test
  public void testAdd4Elem() {
    StatsAccumulator statsAccumulator = new StatsAccumulatorImpl();
    statsAccumulator.add(2);
    statsAccumulator.add(6);
    statsAccumulator.add(-2);
    statsAccumulator.add(9);

    assertAll(
        () -> assertEquals(-2, statsAccumulator.getMin()),
        () -> assertEquals(9, statsAccumulator.getMax()),
        () -> assertEquals(4, statsAccumulator.getCount()),
        () -> assertEquals(3.75, statsAccumulator.getAvg())
    );
  }
}
