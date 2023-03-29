package org.piruntha.services;

import org.piruntha.dto.EmployeeRequest;
import org.piruntha.dto.EmployeeResponse;
import org.piruntha.model.Employee;
import org.piruntha.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String addEmployee(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        employee.setEmpId(employeeRequest.getEmpId());
        employee.setFullName(employeeRequest.getFullName());
        employee.setDob(employeeRequest.getDob());
        employee.setGender(employeeRequest.getGender());
        employee.setEducation(employeeRequest.getEducation());
        employee.setJobTitle(employeeRequest.getJobTitle());
        employee.setStartDate(employeeRequest.getStartDate());
        employee.setSalary(employeeRequest.getSalary());
        employeeRepository.save(employee);
        return "successfully added employee";
    }

    public List<Employee> getEmployees(EmployeeResponse employeeResponse){

        return employeeRepository.findAll();
    }
    public Optional<Employee> getEmployeeByFullName(String fullName){

        return employeeRepository.findEmployeeByFullName(fullName);
    }
    public String editAnEmployee(String fullName, EmployeeRequest employeeRequest){
        Employee existingEmployee = employeeRepository.findEmployeeByFullName(fullName).orElseThrow(()-> new RuntimeException("employee not found"));
        BeanUtils.copyProperties(employeeRequest, existingEmployee);
        existingEmployee.setFullName(fullName);
        employeeRepository.save(existingEmployee);
        return fullName + " details updated";
    }
    public String deleteEmployee(String id){
        employeeRepository.deleteById(id);
        return "Employee deleted";
    }
}
