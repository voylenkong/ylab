package io.ylab.intensive.lesson05.messagefilter.mq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * InitMqImpl
 * <p>
 * Имплементация {@link InitMq}
 *
 * @author VoylenkoNG
 * 02.04.2023
 */
@Component
public class InitMqImpl implements InitMq {

    private ConnectionFactory connectionFactory;

    @Autowired
    public InitMqImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void initMq(String exchangeName, String queueName, String key) {
        try (com.rabbitmq.client.Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, key);
        } catch (IOException | TimeoutException e) {
            System.out.println(e.getMessage());
        }
    }
}
