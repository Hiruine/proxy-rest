package com.pad.restapp.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@JsonPropertyOrder("department")
public class Department {

    @Id
    private Long id;

    @JsonProperty
    private String name;

    private List<Employee> employees;
}



