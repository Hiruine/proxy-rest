package com.pad.restapp.domain.model;

import com.pad.restapp.domain.util.AvailabilityStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
