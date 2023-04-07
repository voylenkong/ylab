package io.ylab.intensive.lesson03.orgstructure.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Employee
 * Класс описывающий сотрудника организации с указанием руководителя и списка подчиненных
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class Employee {

    /**
     * id сотрудника
     */
    private Long id;
    /**
     * id руководителя
     */
    private Long bossId;
    /**
     * Имя сотрудника
     */
    private String name;
    /**
     * Позиция на которой работает сотрудник
     */
    private String position;
    /**
     * Руководитель сотрудника {@link Employee}
     */
    private Employee boss;
    /**
     * Список подчиненных сотрудников
     */
    private List<Employee> subordinate = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBossId() {
        return bossId;
    }

    public void setBossId(Long bossId) {
        this.bossId = bossId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public List<Employee> getSubordinate() {
        return subordinate;
    }

    @Override
    public String toString() {
        String bossNameStr = "-";
        if (Objects.nonNull(boss)) {
            bossNameStr = boss.name;
        }

        StringBuilder subordinateList = new StringBuilder();
        if (Objects.nonNull(subordinate)) {
            for (Employee employee : subordinate) {
                subordinateList.append('\n');
                subordinateList.append(employee.name);
            }
        } else {
            subordinateList.append("-");
        }

        return "Имя: " + name + '\n' +
                "Должность: " + position + '\n' +
                "Руководитель: " + bossNameStr + '\n' +
                "Подчиненные: " + subordinateList;
    }
}
