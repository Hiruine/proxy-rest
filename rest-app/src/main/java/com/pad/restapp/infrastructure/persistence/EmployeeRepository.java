package com.pad.restapp.infrastructure.persistence;

import com.pad.restapp.domain.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {



}
