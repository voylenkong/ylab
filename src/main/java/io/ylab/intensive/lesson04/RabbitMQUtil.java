package io.ylab.intensive.lesson04;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQUtil
 * Инициализация MQ
 *
 * @author YLab
 * 25.03.2023
 */
public class RabbitMQUtil {

    /*
     * Настройки подключения НЕ МЕНЯЕМ!
     * Надо настроить RabbitMQ таким образом, чтобы он работал со следующими
     * настройками
     */
    public static ConnectionFactory buildConnectionFactory() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.newConnection().close();
        return connectionFactory;
    }
}
