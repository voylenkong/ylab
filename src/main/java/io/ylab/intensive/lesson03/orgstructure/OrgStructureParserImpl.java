package io.ylab.intensive.lesson03.orgstructure;

import io.ylab.intensive.lesson03.orgstructure.model.Employee;
import io.ylab.intensive.lesson03.orgstructure.parser.CsvHeaders;
import io.ylab.intensive.lesson03.orgstructure.parser.CsvParser;

import java.io.*;
import java.util.*;

/**
 * OrgStructureParserImpl
 * <p>
 * Имплементация {@link OrgStructureParser}
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class OrgStructureParserImpl implements OrgStructureParser {

    Map<Long, Employee> orgStruct = new HashMap<>();

    Map<Long, List<Long>> bossIdToSubId = new HashMap<>();

    @Override
    public Employee parseStructure(File csvFile) {
        CsvParser csvParser = new CsvParser();
        Employee boss = null;

        var records = csvParser.parseCsv(csvFile, ";");

        for (Map<String, String> record : records) {
            Employee employee = getEmployee(record);
            fillOrgStructure(employee);
            if (Objects.isNull(employee.getBossId())) {
                boss = employee;
            }
        }
        setLinksBetweenEmployees();
        return boss;
    }

    /**
     * Создать сотрудника {@link Employee} из значений его полей
     *
     * @param record Значения полей сотрудника
     * @return Сотрудник {@link Employee}
     */
    private static Employee getEmployee(Map<String, String> record) {
        Employee employee = new Employee();
        employee.setId(Long.valueOf(record.get(CsvHeaders.HEADER_ID.getDescription())));
        if (!record.get(CsvHeaders.HEADER_BOSS_ID.getDescription()).isEmpty()) {
            employee.setBossId(Long.valueOf(record.get(CsvHeaders.HEADER_BOSS_ID.getDescription())));
        }
        employee.setName(record.get(CsvHeaders.HEADER_NAME.getDescription()));
        employee.setPosition(record.get(CsvHeaders.HEADER_POSITION.getDescription()));
        return employee;
    }

    /**
     * Заполнение организационной структуры на основании данных сотрудника
     *
     * @param employee Сотрудник {@link Employee}
     */
    private void fillOrgStructure(Employee employee) {
        if (!bossIdToSubId.containsKey(employee.getBossId())) {
            bossIdToSubId.put(employee.getBossId(), new ArrayList<>());
        }
        bossIdToSubId.get(employee.getBossId()).add(employee.getId());

        orgStruct.put(employee.getId(), employee);
    }

    /**
     * Установление связей между сотрудниками
     */
    private void setLinksBetweenEmployees() {
        for (Employee employee : orgStruct.values()) {
            List<Long> subList = bossIdToSubId.get(employee.getId());
            if (Objects.nonNull(subList)) {
                subList.forEach(subId -> employee.getSubordinate().add(orgStruct.get(subId)));
            }
            employee.setBoss(orgStruct.get(employee.getBossId()));
        }
    }
}
