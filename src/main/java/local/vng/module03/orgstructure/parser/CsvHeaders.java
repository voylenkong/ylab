package local.vng.module03.orgstructure.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CsvHeaders
 *
 * @author VoylenkoNG
 * 20.03.2023
 */
@Getter
@AllArgsConstructor
public enum CsvHeaders {
    HEADER_ID("id"),
    HEADER_BOSS_ID("boss_id"),
    HEADER_NAME("name"),
    HEADER_POSITION("position");

    private final String description;
}
