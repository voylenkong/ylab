package io.ylab.intensive.lesson03.transliterator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TransliteratorImpl
 * <p>
 * Имплементация {@link Transliterator}
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class TransliteratorImpl implements Transliterator {

    @Override
    public String transliterate(String source) {

        Map<Character, String> transliterateTable = new HashMap<>();
        transliterateTable.put('А', "A");
        transliterateTable.put('Б', "B");
        transliterateTable.put('В', "V");
        transliterateTable.put('Г', "G");
        transliterateTable.put('Д', "D");
        transliterateTable.put('Е', "E");
        transliterateTable.put('Ё', "E");
        transliterateTable.put('Ж', "ZH");
        transliterateTable.put('З', "Z");
        transliterateTable.put('И', "I");
        transliterateTable.put('Й', "I");
        transliterateTable.put('К', "K");
        transliterateTable.put('Л', "L");
        transliterateTable.put('М', "M");
        transliterateTable.put('Н', "N");
        transliterateTable.put('О', "O");
        transliterateTable.put('П', "P");
        transliterateTable.put('Р', "R");
        transliterateTable.put('С', "S");
        transliterateTable.put('Т', "T");
        transliterateTable.put('У', "U");
        transliterateTable.put('Ф', "F");
        transliterateTable.put('Х', "KH");
        transliterateTable.put('Ц', "TS");
        transliterateTable.put('Ч', "CH");
        transliterateTable.put('Ш', "SH");
        transliterateTable.put('Щ', "SHCH");
        transliterateTable.put('Ы', "Y");
        transliterateTable.put('Ь', "");
        transliterateTable.put('Ъ', "IE");
        transliterateTable.put('Э', "E");
        transliterateTable.put('Ю', "IU");
        transliterateTable.put('Я', "IA");

        return source
                .chars()
                .mapToObj(c -> (char) c)
                .map(c -> transliterateTable.getOrDefault(c, c.toString()))
                .collect(Collectors.joining());
    }
}
