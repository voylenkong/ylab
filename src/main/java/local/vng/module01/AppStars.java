package local.vng.module01;

import local.vng.module01.util.Validator;
import local.vng.module01.stars.Stars;

import java.util.Scanner;

/**
 * AppStars
 *
 * @author VoylenkoNG
 * 04.03.2023
 */
public class AppStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число n: ");
        String nStr = scanner.next();
        System.out.print("Введите число m: ");
        String mStr = scanner.next();
        System.out.print("Введите символ: ");
        String cStr = scanner.next();

        if (Validator.isValidNumber(nStr) &&
                Validator.isValidNumber(mStr) &&
                Validator.isValidSize(cStr)) {
            Stars.printShape(Integer.parseInt(nStr), Integer.parseInt(mStr), cStr);
        } else {
            System.out.println("Некорректный ввод.");
        }
    }
}
