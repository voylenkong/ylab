package local.vng.module03.orgstructure;

import local.vng.module03.orgstructure.model.Employee;

import java.io.File;
import java.io.IOException;

/**
 * OrgStructureParser
 * <p>
 * Загрузка структуры организации из CSV файла.
 * Каждая строка представляет собой одну запись (объект).
 * Поля объекта разделены специальным символом ;. Первая строка файла содержит поля имена полей,
 * все дальнейшие сроки содержат непосредственно данные.
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public interface OrgStructureParser {

    /**
     * Метод считывает данные из файла и возвращать ссылку на
     * Босса (Генерального директора) - сотрудника, атрибут boss_id которого не задан.
     *
     * @param csvFile Входной csv файл со структурой организации
     * @return Босса (Генеральный директор) {@link Employee}
     * @throws IOException
     */
    Employee parseStructure(File csvFile) throws IOException;
}
