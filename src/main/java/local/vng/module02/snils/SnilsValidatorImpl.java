package local.vng.module02.snils;

/**
 * SnilsValidatorImpl
 * <p>
 * Имплементация {@link SnilsValidator}
 *
 * @author VoylenkoNG 11.03.2023
 */
public class SnilsValidatorImpl implements SnilsValidator {

    @Override
    public boolean validate(String snils) {
        boolean result = false;
        if (snils.matches("\\b\\d{11}\\b")) {

            int checksum = Integer.parseInt(snils.substring(9));
            if (this.calculateChecksum(snils) == checksum) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Вычисление контрольной суммы СНИЛС
     *
     * @param snils Строка содержащая СНИЛС
     * @return Контрольная сумма СНИЛС
     */
    private int calculateChecksum(String snils) {
        char[] snilsArr = snils.toCharArray();
        int signPart = 0;
        int calculatedChecksum = 0;

        for (int i = 0, j = 9; i < 9; i++, j--) {
            signPart += (Character.digit(snilsArr[i], 10)) * j;
        }

        if (signPart < 100) {
            calculatedChecksum = signPart;
        } else if (signPart == 100) {
            calculatedChecksum = 0;
        } else {
            calculatedChecksum = signPart % 101;
            if (calculatedChecksum == 100) {
                calculatedChecksum = 0;
            }
        }
        return calculatedChecksum;
    }
}
