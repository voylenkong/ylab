package local.vng.module03.transliterator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * TransliteratorTest
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class TransliteratorTest {

    @Test
    public void testTransliterate() {
        var transliterator = new TransliteratorImpl();

        String original1 = "HELLO! ПРИВЕТ! Go, boy!";
        String transliterated1 = "HELLO! PRIVET! Go, boy!";
        String original2 = "HELLO! привет! Go, boy!";
        String transliterated2 = "HELLO! привет! Go, boy!";
        String original3 = "Иди ИЩИ ГОРЯЧУЮ ПЕЧЬ!";
        String transliterated3 = "Iди ISHCHI GORIACHUIU PECH!";
        String original4 = "не для транслитерации, not for transliteration!";
        String transliterated4 = "не для транслитерации, not for transliteration!";

        assertAll(
                () -> assertEquals(transliterated1, transliterator.transliterate(original1)),
                () -> assertEquals(transliterated2, transliterator.transliterate(original2)),
                () -> assertEquals(transliterated3, transliterator.transliterate(original3)),
                () -> assertEquals(transliterated4, transliterator.transliterate(original4))
        );
    }
}
