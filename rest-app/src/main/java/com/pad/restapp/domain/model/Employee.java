package com.pad.restapp.domain.model;

import com.pad.restapp.domain.util.AvailabilityStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private long departmentId;
    private AvailabilityStatus status;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return departmentId == employee.departmentId &&
                Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                Objects.equals(age, employee.age) &&
                status == employee.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, departmentId, status);
    }
}
