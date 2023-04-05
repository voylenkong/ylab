package io.ylab.intensive.lesson05.eventsourcing.api;

import io.ylab.intensive.lesson05.eventsourcing.Person;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

import static java.util.Objects.nonNull;

public class ApiApp {
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        PersonApi personApi = applicationContext.getBean(PersonApi.class);

        Scanner scanner = new Scanner(System.in);
        String operation;

        boolean isContinue = true;
        while (isContinue) {
            System.out.println("del     - удалить пользователя по id ");
            System.out.println("save    - сохранить пользователя ");
            System.out.println("find    - найти пользователя по id ");
            System.out.println("findAll - найти всех пользователей ");
            System.out.println("exit    - выход");
            System.out.print("Введите операцию: ");
            operation = scanner.next();

            switch (operation) {
                case ("del") -> {
                    System.out.print("Введите id: ");
                    Long personId = Long.valueOf(scanner.next());
                    personApi.deletePerson(personId);
                }
                case ("save") -> {
                    System.out.print("Введите id: ");
                    Long personId = Long.valueOf(scanner.next());

                    System.out.print("Введите firstName: ");
                    String firstName = scanner.next();

                    System.out.print("Введите lastName: ");
                    String lastName = scanner.next();

                    System.out.print("Введите middleName: ");
                    String middleName = scanner.next();

                    personApi.savePerson(personId, firstName, lastName, middleName);
                }
                case ("find") -> {
                    System.out.print("Введите id: ");
                    Long personId = Long.valueOf(scanner.next());
                    Person person = personApi.findPerson(personId);
                    if (nonNull(person)) {
                        System.out.println(person);
                    } else {
                        System.out.println("Пользователь с id " + personId + " не существует");
                    }
                }
                case ("findAll") -> {
                    System.out.println(personApi.findAll());
                }
                case ("exit") -> {
                    isContinue = false;
                }
            }
        }

        scanner.close();
        applicationContext.close();
    }
}
