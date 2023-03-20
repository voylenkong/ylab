package local.vng.module03.datedmap;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DatedMapTest
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class DatedMapTest {
    private DatedMap datedMap;
    private Date lastInsertDate;

    private void init() {
        datedMap = new DatedMapImpl();
    }

    private void addFirstElem() {
        datedMap.put("1", "a");
        lastInsertDate = new Date();
    }

    private void addSecondElem() {
        datedMap.put("2", "b");
        lastInsertDate = new Date();
    }

    private void removeFirstElem() {
        datedMap.remove("1");
    }

    /**
     * Ожидание в милисекундах.
     *
     * @param milisec Время в милисекундах
     */
    private void busySpin(int milisec) {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) < milisec) {
        }
    }

    @Test
    public void testInit() {
        init();

        assertAll(
                () -> assertNull(datedMap.get("1")),
                () -> assertFalse(datedMap.containsKey("1")),
                () -> assertTrue(datedMap.keySet().isEmpty())
        );
    }

    @Test
    public void testAddFirstElem() {
        init();

        addFirstElem();

        assertAll(
                () -> assertEquals("a", datedMap.get("1")),
                () -> assertTrue(datedMap.containsKey("1")),
                () -> assertFalse(datedMap.keySet().isEmpty()),
                () -> assertEquals(lastInsertDate, datedMap.getKeyLastInsertionDate("1"))
        );
    }

    @Test
    public void testAddFirstElemAgain() {
        init();

        addFirstElem();
        Date firstInsertDate = lastInsertDate;

        busySpin(5000);

        addFirstElem();
        Date secondInsertDate = lastInsertDate;

        assertAll(
                () -> assertEquals("a", datedMap.get("1")),
                () -> assertTrue(datedMap.containsKey("1")),
                () -> assertFalse(datedMap.keySet().isEmpty()),
                () -> assertNotEquals(firstInsertDate, datedMap.getKeyLastInsertionDate("1")),
                () -> assertEquals(secondInsertDate, datedMap.getKeyLastInsertionDate("1"))
        );
    }

    @Test
    public void testAddSecondElem() {
        var resultSet = new HashSet<>() {{
            add("1");
            add("2");
        }};

        init();

        addFirstElem();
        Date firstElemDate = lastInsertDate;

        busySpin(5000);

        addSecondElem();
        Date secondElemDate = lastInsertDate;

        assertAll(
                () -> assertEquals("a", datedMap.get("1")),
                () -> assertEquals("b", datedMap.get("2")),
                () -> assertTrue(datedMap.containsKey("1")),
                () -> assertTrue(datedMap.containsKey("2")),
                () -> assertEquals(resultSet, datedMap.keySet()),
                () -> assertEquals(firstElemDate, datedMap.getKeyLastInsertionDate("1")),
                () -> assertEquals(secondElemDate, datedMap.getKeyLastInsertionDate("2"))
        );
    }

    @Test
    public void testRemoveFirstElem() {
        var resultSet = new HashSet<>() {{
            add("2");
        }};

        init();

        addFirstElem();

        addSecondElem();
        Date secondElemDate = lastInsertDate;

        removeFirstElem();

        assertAll(
                () -> assertNull(datedMap.get("1")),
                () -> assertFalse(datedMap.containsKey("1")),
                () -> assertEquals("b", datedMap.get("2")),
                () -> assertTrue(datedMap.containsKey("2")),
                () -> assertEquals(resultSet, datedMap.keySet()),
                () -> assertEquals(secondElemDate, datedMap.getKeyLastInsertionDate("2"))
        );
    }
}
