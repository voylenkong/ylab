package local.vng.lesson05.eventsourcing.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MQCreatePerson
 * Класс сообщение создание персоны для MQ
 *
 * @author VoylenkoNG
 * 26.03.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MQCreatePerson {
    private Long id;
    private String name;
    private String lastName;
    private String middleName;
}
