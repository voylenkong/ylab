package local.vng.module02.complex;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * ComplexNumberTest
 *
 * @author VoylenkoNG
 * 11.03.2023
 */
public class ComplexNumberTest {
  
  @Test
  public void testAdd() {
    var a1 = new ComplexNumber(5, -6);
    var b1 = new ComplexNumber(-3, 2);
    var a2 = new ComplexNumber(3, 1);
    var b2 = new ComplexNumber(5, -2);

    var result1 = new ComplexNumber(2,-4);
    var result2 = new ComplexNumber(8,-1);

    assertAll(
        () -> assertEquals(result1, a1.add(b1)),
        () -> assertEquals(result2, a2.add(b2))
    );
  }

  @Test
  public void testSub() {
    var a1 = new ComplexNumber(5, -6);
    var b1 = new ComplexNumber(-3, 2);
    var a2 = new ComplexNumber(3, 1);
    var b2 = new ComplexNumber(5, -2);

    var result1 = new ComplexNumber(8,-8);
    var result2 = new ComplexNumber(-2,3);

    assertAll(
        () -> assertEquals(result1, a1.sub(b1)),
        () -> assertEquals(result2, a2.sub(b2))
    );
  }

  @Test
  public void testMul() {
    var a1 = new ComplexNumber(2, 3);
    var b1 = new ComplexNumber(-1, 1);
    var a2 = new ComplexNumber(3, 1);
    var b2 = new ComplexNumber(5, -2);

    var result1 = new ComplexNumber(-5,-1);
    var result2 = new ComplexNumber(17,-1);

    assertAll(
        () -> assertEquals(result1, a1.mul(b1)),
        () -> assertEquals(result2, a2.mul(b2))
    );
  }

  @Test
  public void testAbs() {
    var a1 = new ComplexNumber(4, -4);
    var a2 = new ComplexNumber(2, -3);

    assertAll(
        () -> assertEquals(5.65685, a1.abs(), 0.00001),
        () -> assertEquals(3.60555, a2.abs(),0.00001)
    );
  }
}
