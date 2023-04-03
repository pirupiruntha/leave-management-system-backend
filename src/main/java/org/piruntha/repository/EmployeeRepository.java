package org.piruntha.repository;

import org.piruntha.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findEmployeeByUsername(String username);

}

