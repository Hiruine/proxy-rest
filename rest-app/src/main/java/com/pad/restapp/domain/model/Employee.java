package com.pad.restapp.domain.model;

import com.pad.restapp.domain.util.AvailabilityStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

@Document
public class Employee extends ResourceSupport {

    @Id
    private String id;
    private String name;
    private Integer age;
    private long departmentId;
    private AvailabilityStatus status;

    public Employee(String id, String name, Integer age, long departmentId, AvailabilityStatus status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.departmentId = departmentId;
        this.status = status;
    }

    public String getEmployeeId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public AvailabilityStatus getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public void setStatus(AvailabilityStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return departmentId == employee.departmentId &&
                Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age) &&
                status == employee.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, departmentId, status);
    }
}
