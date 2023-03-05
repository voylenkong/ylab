package local.vng.module01;

import local.vng.module01.util.Validator;
import local.vng.module01.pell.Pell;

import java.util.Scanner;

/**
 * AppPell
 *
 * @author VoylenkoNG
 * 04.03.2023
 */
public class AppPell {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число n: ");
        String nStr = scanner.next();

        if (Validator.isValidNumber(nStr) && (Integer.parseInt(nStr) <= 30)) {
            System.out.print("n-е число Пелля: ");
            System.out.println(Pell.getPell(Integer.parseInt(nStr)));
        } else {
            System.out.println("Некорректный ввод числа n.");
        }
    }
}
