package local.vng.module02.complexnumber;

import lombok.Getter;

/**
 * ComplexNumber
 *
 * @author VoylenkoNG
 * 11.03.2023
 */

@Getter
public class ComplexNumber {

  /**
   * Вещественная часть комплексного числа
   */
  private double re;
  /**
   * Мнимая часть комплексного числа
   */
  private double im;

  /**
   * Конструктор комплексного числа по вещественной части комплексного числа
   *
   * @param re Вещественная часть комплексного числа
   */
  public ComplexNumber(double re) {
    this.re = re;
    this.im = 0;
  }

  /**
   * Конструктор комплексного числа по вещественной и мнимой части комплексного числа
   *
   * @param re Вещественная часть комплексного числа
   * @param im Мнимая часть комплексного числа
   */
  public ComplexNumber(double re, double im) {
    this.re = re;
    this.im = im;
  }

  /**
   * Сложение текущего комплексного числа с комплексным числом a
   *
   * @param a Комплексное число a {@link ComplexNumber}
   * @return Сумма текущего комплексного числа и комплексного числа a {@link ComplexNumber}
   */
  public ComplexNumber add(ComplexNumber a) {
    var rePart = this.getRe() + a.getRe();
    var imPart = this.getIm() + a.getIm();
    return new ComplexNumber(rePart, imPart);
  }

  /**
   * Вычитание из текущего комплексного числа комплексного числа a
   *
   * @param a Комплексное число a {@link ComplexNumber}
   * @return Разность дтекущего комплексного числа и комплексного числа a {@link ComplexNumber}
   */
  public ComplexNumber sub(ComplexNumber a) {
    var rePart = this.getRe() - a.getRe();
    var imPart = this.getIm() - a.getIm();
    return new ComplexNumber(rePart, imPart);
  }

  /**
   * Умножение текущего комплексного числа на комплексное число a
   *
   * @param a Комплексное число a {@link ComplexNumber}
   * @return Произведение текущего комплексного числа на комплексное число a {@link ComplexNumber}
   */
  public ComplexNumber mul(ComplexNumber a) {
    var rePart = this.getRe() * a.getRe() - this.getIm() * a.getIm();
    var imPart = this.getIm() * a.getRe() + this.getRe() * a.getIm();
    return new ComplexNumber(rePart, imPart);
  }

  /**
   * Получение модуля текущего комплексного числа
   *
   * @return Модуль текущего комплексного числа
   */
  public double abs() {
    return Math.sqrt(this.getRe() * this.getRe() + this.getIm() * this.getIm());
  }

  @Override
  public String toString() {
    if (im >= 0) {
      return re + "+" + im + "i";
    } else {
      return re + "" + im + "i";
    }
  }
}
