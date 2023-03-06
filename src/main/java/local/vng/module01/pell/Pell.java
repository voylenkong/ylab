package local.vng.module01.pell;

/**
 * Pell
 *
 * @author VoylenkoNG
 * 04.03.2023
 */
public class Pell {

    /**
     * Вычисление n-ого числа Пелля. (0 <= n <= 30)
     *
     * @param   n   Входящее число для вычисления n-ого числа Пелля
     * @return  n-ое число Пелля
     */
    public static long getPell(int n) {
        long result = 0;

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n >= 2) {
            long prev = 1;
            long beforePrev = 0;
            result = 1;
            for (int i = 1; i < n; i++) {
                result = 2 * prev + beforePrev;
                beforePrev = prev;
                prev = result;
            }
        }
        return result;
    }

}
