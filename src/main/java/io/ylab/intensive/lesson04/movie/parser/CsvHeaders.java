package io.ylab.intensive.lesson04.movie.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CsvHeaders
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
@Getter
@AllArgsConstructor
public enum CsvHeaders {
    HEADER_YEAR("Year"),
    HEADER_LENGTH("Length"),
    HEADER_TITLE("Title"),
    HEADER_SUBJECT("Subject"),
    HEADER_ACTOR("Actor"),
    HEADER_ACTRESS("Actress"),
    HEADER_DIRECTOR("Director"),
    HEADER_POPULARITY("Popularity"),
    HEADER_AWARDS("Awards"),
    HEADER_IMAGE("Image");

    private final String description;
}
