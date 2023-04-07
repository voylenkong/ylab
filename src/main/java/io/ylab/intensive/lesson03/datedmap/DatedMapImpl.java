package io.ylab.intensive.lesson03.datedmap;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * DatedMapImpl
 * <p>
 * Имплементация {@link DatedMap}
 *
 * @author VoylenkoNG
 * 18.03.2023
 */

public class DatedMapImpl implements DatedMap {

    private HashMap<String, String> keyValueMap;

    private HashMap<String, Date> keyDateMap;

    public DatedMapImpl() {
        keyValueMap = new HashMap<>();
        keyDateMap = new HashMap<>();
    }

    @Override
    public void put(String key, String value) {
        keyValueMap.put(key, value);
        keyDateMap.put(key, new Date());
    }

    @Override
    public String get(String key) {
        return keyValueMap.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return keyValueMap.containsKey(key);
    }

    @Override
    public void remove(String key) {
        keyValueMap.remove(key);
        keyDateMap.remove(key);
    }

    @Override
    public Set<String> keySet() {
        return keyValueMap.keySet();
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        return keyDateMap.get(key);
    }
}
