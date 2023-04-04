package org.piruntha.services;

import org.piruntha.dto.requests.EmployeeRequest;
import org.piruntha.dto.responses.EmployeeResponse;
import org.piruntha.model.Employee;
import org.piruntha.model.UserRoles;
import org.piruntha.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addEmployee(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        employee.setEmpId(employeeRequest.getEmpId());
        employee.setFullName(employeeRequest.getFullName());
        employee.setDob(employeeRequest.getDob());
        employee.setUsername(employeeRequest.getUsername());
        employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        employee.setGender(employeeRequest.getGender());
        employee.setEmail(employeeRequest.getEmail());
        employee.setEducation(employeeRequest.getEducation());
        employee.setJobTitle(employeeRequest.getJobTitle());
        employee.setStartDate(employeeRequest.getStartDate());
        employee.setSalary(employeeRequest.getSalary());
        employee.setRoles(List.of(UserRoles.ROLE_USER));
        employeeRepository.save(employee);
        return "successfully added employee";
    }

    public List<Employee> getEmployees(EmployeeResponse employeeResponse){

        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeByUsername(String username){

        return employeeRepository.findEmployeeByUsername(username);
    }


    public String editAnEmployee(String username, EmployeeRequest employeeRequest){
        Employee existingEmployee = employeeRepository.findEmployeeByUsername(username).orElseThrow(()-> new RuntimeException("employee not found"));
        BeanUtils.copyProperties(employeeRequest, existingEmployee);
        existingEmployee.setFullName(username);
        employeeRepository.save(existingEmployee);
        return username + " details updated";
    }
    public String deleteEmployee(String id){
        employeeRepository.deleteById(id);
        return "Employee deleted";
    }
}
