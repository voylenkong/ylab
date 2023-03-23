package local.vng.module03.transliterator;

/**
 * Transliterator
 * <p>
 * Транслитерация входной строки в выходную, то
 * есть заменяется каждый символ кириллицы на соответствующую группу символов
 * латиницы. Каждый символ кириллицы, имеющийся во входной строке входит в нее в
 * верхнем регистре.
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public interface Transliterator {

    /**
     * Транслитерация входной строки
     *
     * @param source Входная строка
     * @return Строка после транслитерации
     */
    String transliterate(String source);
}
