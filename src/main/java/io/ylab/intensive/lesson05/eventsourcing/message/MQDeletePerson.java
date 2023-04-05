package io.ylab.intensive.lesson05.eventsourcing.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MQDeletePerson
 * Класс сообщение удаление персоны для MQ
 *
 * @author VoylenkoNG
 * 26.03.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MQDeletePerson {
    private Long id;
}
