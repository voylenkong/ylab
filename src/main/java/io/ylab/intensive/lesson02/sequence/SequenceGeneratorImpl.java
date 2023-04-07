package io.ylab.intensive.lesson02.sequence;

import java.math.BigInteger;

/**
 * Имплементация {@link SequenceGenerator}
 *
 * @author VoylenkoNG 11.03.2023
 */
public class SequenceGeneratorImpl implements SequenceGenerator {

    @Override
    public void a(int n) {

        for (long i = 2; n > 0; i = i + 2, n--) {
            System.out.print(i);

            if (n != 1) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void b(int n) {

        for (long i = 1; n > 0; i = i + 2, n--) {
            System.out.print(i);

            if (n != 1) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void c(int n) {

        for (long i = 1; i <= n; i++) {
            //Умножение работает быстрее Math.pow()
            System.out.print(i * i);

            if (i != n) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void d(int n) {

        for (long i = 1; i <= n; i++) {
            //Умножение работает быстрее Math.pow()
            System.out.print(i * i * i);

            if (i != n) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void e(int n) {

        for (int i = 1; i <= n; i++) {
            System.out.print(this.getCoefficientForOddEven(i));

            if (i != n) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void f(int n) {

        for (long i = 1; i <= n; i++) {
            System.out.print(this.getCoefficientForOddEven(i) * i);

            if (i != n) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void g(int n) {

        for (long i = 1; i <= n; i++) {
            //Умножение работает быстрее Math.pow()
            System.out.print(this.getCoefficientForOddEven(i) * i * i);

            if (i != n) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void h(int n) {
        for (long i = 1, j = 1; i <= n; i++) {
            if (i % 2 != 0) {
                System.out.print(j);
                j++;
            } else {
                System.out.print(0);
            }

            if (i != n) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void i(int n) {

        BigInteger prev = BigInteger.ONE;
        for (int i = 1; n > 0; i++, n--) {
            prev = prev.multiply(BigInteger.valueOf(i));
            System.out.print(prev);

            if (n != 1) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public void j(int n) {

        BigInteger current = BigInteger.ONE;
        BigInteger prev = BigInteger.ONE;
        BigInteger beforePrev = BigInteger.ZERO;
        for (int i = 1; n > 0; i++, n--) {
            System.out.print(current);

            current = beforePrev.add(prev);
            beforePrev = prev;
            prev = current;

            if (n != 1) {
                System.out.print(", ");
            }
        }
    }

    /**
     * Вычисление коэффицианта четного/нечетного числа
     *
     * @param n Входящее число
     * @return 1 если нечетное, -1 если четное
     */
    private int getCoefficientForOddEven(long n) {
        if (n % 2 != 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
