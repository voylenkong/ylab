package local.vng.module03;

import local.vng.module03.datedmap.DatedMap;
import local.vng.module03.datedmap.DatedMapImpl;

import java.util.Scanner;

/**
 * DatedMapTest
 * <p>
 * DatedMap - это структура данных, очень похожая на Map, но содержащая
 * дополнительную информацию: время добавления каждого ключа. При этом время
 * хранится только для тех ключей, которые присутствуют в Map.
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class DatedMapTest {
    public static void main(String[] args) {
        DatedMap datedMap = new DatedMapImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Допустимые операции (put, get, containsKey, remove, getKeySet, getKeyDateInsert):");
        System.out.println("put - поместить пару ключ и значение ");
        System.out.println("get - вернуть значение по ключу");
        System.out.println("containsKey - есть ли значение для данного ключа");
        System.out.println("remove - удалить пару ключ и значение");
        System.out.println("getKeySet - получить Set ключей");
        System.out.println("getKeyDateInsert - получить добавления пары ключ и значение");
        System.out.println("exit - выход");

        String operation;

        while (true) {
            System.out.print("Введите операцию: ");
            operation = scanner.next();

            switch (operation) {
                case ("put") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();
                    System.out.print("Введите значение: ");
                    String value = scanner.next();

                    datedMap.put(key, value);
                    System.out.println("Пара ключ и значение сохранена");
                }
                case ("get") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();

                    if (datedMap.containsKey(key)) {
                        System.out.println("Значение по ключу " + key + " : " + datedMap.get(key));
                    } else {
                        System.out.println("Значение по ключу " + key + " отсутствует");
                    }
                }
                case ("containsKey") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();

                    if (datedMap.containsKey(key)) {
                        System.out.println("Значение по ключу " + key + " присутствует");
                    } else {
                        System.out.println("Значение по ключу " + key + " отсутсвует");
                    }
                }
                case ("remove") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();

                    if (datedMap.containsKey(key)) {
                        datedMap.remove(key);
                        System.out.println("Пара ключ и значение удалена");
                    } else {
                        System.out.println("Значение по ключу " + key + " отсутсвует");
                    }


                }
                case ("getKeySet") -> {
                    System.out.println("Множество ключей: " + datedMap.keySet());
                }
                case ("getKeyDateInsert") -> {
                    System.out.print("Введите ключ: ");
                    String key = scanner.next();

                    if (datedMap.containsKey(key)) {
                        System.out.println("Дата добавления ключа: " + key + " " + datedMap.getKeyLastInsertionDate(key));
                    } else {
                        System.out.println("Значение по ключу " + key + " отсутсвует");
                    }

                }
            }

            if (operation.equals("exit")) {
                break;
            }
        }

        scanner.close();
    }
}
