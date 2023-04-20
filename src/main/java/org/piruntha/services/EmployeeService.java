package org.piruntha.services;

import org.piruntha.dto.requests.EmployeeRequest;
import org.piruntha.dto.responses.DeleteResponse;
import org.piruntha.dto.responses.EmployeeResponse;
import org.piruntha.exceptions.CustomException;
import org.piruntha.exceptions.EmailExistsException;
import org.piruntha.model.Employee;
import org.piruntha.model.UserRoles;
import org.piruntha.repository.EmployeeRepository;
import org.piruntha.repository.LeaveRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    LeaveRepository leaveRepository;

    @Value("${leave.allowanceMax}")
    private int leaveAllowanceMax;

    public Employee addEmployee(EmployeeRequest employeeRequest) throws EmailExistsException {
        Employee employee = new Employee();
        employee.setEmpId(employeeRequest.getEmpId());
        employee.setFullName(employeeRequest.getFullName());
        employee.setDob(employeeRequest.getDob());
        employee.setUsername(employeeRequest.getUsername());
        employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        employee.setGender(employeeRequest.getGender());
        employee.setEmail(employeeRequest.getEmail());
        if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            throw new CustomException("Email already exists", 401);
        }
        employee.setEducation(employeeRequest.getEducation());
        employee.setTelephoneNo(employeeRequest.getTelephoneNo());
        employee.setJobTitle(employeeRequest.getJobTitle());
        if(employeeRequest.getLeaveAllowance()<=leaveAllowanceMax){
            employee.setLeaveAllowance(employeeRequest.getLeaveAllowance());
        } else {
            throw new CustomException("Leave allowance not allow more than 21");
        }
        employee.setLeaveBalance(employeeRequest.getLeaveAllowance());
        employee.setStartDate(employeeRequest.getStartDate());
        employee.setSalary(employeeRequest.getSalary());
        employee.setRoles(List.of(UserRoles.ROLE_USER));
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> getEmployees(EmployeeResponse employeeResponse){

        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeByUsername(String username){

        return employeeRepository.findEmployeeByUsername(username);
    }

    public Employee editAnEmployee(String username, EmployeeRequest employeeRequest){
        Employee existingEmployee = employeeRepository.findEmployeeByUsername(username).orElseThrow(()-> new CustomException("employee not found " + username, 404));
        String password = existingEmployee.getPassword();
        BeanUtils.copyProperties(employeeRequest, existingEmployee);
        existingEmployee.setUsername(username);
        existingEmployee.setPassword(password);
        existingEmployee.setRoles(List.of(UserRoles.ROLE_USER));

        return employeeRepository.save(existingEmployee);
    }
    public DeleteResponse deleteEmployee(String username){
        leaveRepository.deleteAllByEmpUsername(username);
        employeeRepository.deleteById(username);
        return new DeleteResponse("delete success");
    }
}
