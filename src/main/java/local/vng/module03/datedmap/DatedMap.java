package local.vng.module03.datedmap;

import java.util.Date;
import java.util.Set;

/**
 * DatedMap
 * <p>
 * DatedMap - это структура данных, очень похожая на Map, но содержащая
 * дополнительную информацию: время добавления каждого ключа. При этом время
 * хранится только для тех ключей, которые присутствуют в Map.
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public interface DatedMap {

    /**
     * Помещает в map пару ключ и значение. Как видно из описания метода, ключ и
     * значение - это строки (семантика Map#put)
     *
     * @param key   Ключ
     * @param value Значение
     */
    void put(String key, String value);

    /**
     * Возвращает значение, связанное с переданным в метод ключом. Если для
     * переданного ключа значение отсутствует - возвращается null (семантика Map#get)
     *
     * @param key Ключ
     * @return Значение
     */
    String get(String key);

    /**
     * Метод, проверяющий, есть ли в map значение для данного ключа
     * (семантика Map#containsKey)
     *
     * @param key Ключ
     * @return Значение
     */
    boolean containsKey(String key);

    /**
     * Получая на вход ключ, удаляет из map ключ и значение, с ним связанное
     * (семантика Map#remove)
     *
     * @param key Ключ
     */
    void remove(String key);

    /**
     * Возвращает множество ключей, присутствующее в map (семантика
     * Map#keySet)
     *
     * @return Сет ключей
     */
    Set<String> keySet();

    /**
     * Получая на вход ключ, проверяет, что для данного ключа
     * существует значение в map. Если существует - возвращает дату, когда оно было
     * добавлено. Если нет - возвращает null
     *
     * @param key Ключ
     * @return Дата добавления
     */
    Date getKeyLastInsertionDate(String key);
}
