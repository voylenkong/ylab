package io.ylab.intensive.lesson05.messagefilter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service
 *
 * @author VoylenkoNG
 * 01.04.2023
 */
@Component
public class CensorServiceImpl implements CensorService {

    private DataSource dataSource;
    private static final String SELECT_WORD = "SELECT COUNT(word) FROM bad_words WHERE word=?";
    private static final String DELIMITERS =
            "((?=\\.|,|\\?|!| |-|;|<|>|\"|\"|\\(|\\)|\\{|}|'|-|\\+|=|\n|\r)|(?<=\\.|,|\\?|!| |-|;|<|>|\"|\"|\\(|\\)|\\{|}|'|-|\\+|=|\n|\r))";

    @Autowired
    public CensorServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String censorSentence(String inputSentence) {

        String[] inputSentenceAr = inputSentence.split(DELIMITERS);
        for (int i = 0; i < inputSentenceAr.length; i++) {
            if (hasBadWord(inputSentenceAr[i].toLowerCase())) {
                inputSentenceAr[i] = censorBadWord(inputSentenceAr[i]);
            }
        }
        return stringArrayToString(inputSentenceAr);
    }

    /**
     * Цензурирование слова по типу F**k
     *
     * @param inputWord Входящее слово
     * @return Цензурируемое слово
     */
    private String censorBadWord(String inputWord) {
        char[] censoredWordAr = inputWord.toCharArray();
        if (censoredWordAr.length <= 2) {
            return inputWord;
        } else {
            for (int i = 1; i < censoredWordAr.length - 1; i++) {
                censoredWordAr[i] = '*';
            }
        }
        return new String(censoredWordAr);
    }

    /**
     * Проверка является ли слово нецензурным
     *
     * @param word Водящее слово
     * @return Результат boolean
     */
    private boolean hasBadWord(String word) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_WORD);
        ) {
            ps.setString(1, word);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Перевод массива строк в строку
     *
     * @param input Массив строк
     * @return Строка из массива строк
     */
    private String stringArrayToString(String[] input) {
        StringBuilder sb = new StringBuilder();
        for (String elem : input) {
            sb.append(elem);
        }
        return sb.toString();
    }

}
