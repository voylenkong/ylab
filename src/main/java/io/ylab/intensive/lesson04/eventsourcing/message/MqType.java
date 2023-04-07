package io.ylab.intensive.lesson04.eventsourcing.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MqType
 *
 * @author VoylenkoNG
 * 28.03.2023
 */
@Getter
@AllArgsConstructor
public enum MqType {
    CREATE("Create"),
    DELETE("Delete");
    private final String description;
}
