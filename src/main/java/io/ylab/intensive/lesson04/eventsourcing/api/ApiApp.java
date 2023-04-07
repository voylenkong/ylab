package io.ylab.intensive.lesson04.eventsourcing.api;

import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.DbUtil;
import io.ylab.intensive.lesson04.eventsourcing.Person;
import io.ylab.intensive.lesson04.RabbitMQUtil;
import org.apache.log4j.BasicConfigurator;

import javax.sql.DataSource;
import java.util.Scanner;

import static java.util.Objects.nonNull;

/**
 * ApiApp
 * <p>
 * Приложение содержит реализацию следующего интерфейса:
 * PersonApi
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class ApiApp {
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        ConnectionFactory connectionFactory = initMQ();
        DataSource dataSource = DbUtil.buildDataSource();
        PersonApi personApi = new PersonApiImpl(dataSource, connectionFactory);

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
                case("exit") -> {
                    isContinue = false;
                }
            }
        }

        scanner.close();
    }

    /**
     * Инициализация MQ
     *
     * @return ConnectionFactory
     * @throws Exception
     */
    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactory();
    }
}
