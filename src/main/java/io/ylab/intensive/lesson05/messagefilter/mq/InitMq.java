package io.ylab.intensive.lesson05.messagefilter.mq;

/**
 * InitMq
 *
 * @author VoylenkoNG
 * 02.04.2023
 */
public interface InitMq {

    /**
     * Инициализация MQ
     *
     * @param exchangeName Имя обменника
     * @param mqName       Имя очереди
     * @param key          Ключ
     */
    void initMq(String exchangeName, String mqName, String key);
}
