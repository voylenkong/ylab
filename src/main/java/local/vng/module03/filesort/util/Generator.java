package local.vng.module03.filesort.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Generator
 * Генерация файла случайными числами
 *
 * @author YLab
 * 19.03.2023
 */
public class Generator {
    /**
     * Генерация файла со случайными числами
     *
     * @param name  Имя файла
     * @param count Количество чисел
     * @return Файл - результат со случайными числами
     * @throws IOException
     */
    public File generate(String name, int count) throws IOException {
        Random random = new Random();
        File file = new File(name);
        try (PrintWriter pw = new PrintWriter(file)) {
            for (int i = 0; i < count; i++) {
                pw.println(random.nextLong());
            }
            pw.flush();
        }
        return file;
    }
}
