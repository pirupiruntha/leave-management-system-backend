package org.piruntha.controllers;

import org.piruntha.dto.requests.EmployeeRequest;
import org.piruntha.dto.responses.EmployeeResponse;
import org.piruntha.model.Employee;
import org.piruntha.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring security";
    }

    @PostMapping()
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.addEmployee(employeeRequest);
    }

    @GetMapping()
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Employee> getAllEmployees(EmployeeResponse employeeResponse){
        List<Employee> employees = employeeService.getEmployees(employeeResponse);
        return employees;
    }

    @GetMapping("/{username}")
   // @PreAuthorize("hasAuthority('ROLE_USER')")
    public Optional<Employee> getAnEmployeeByUsername(@PathVariable String username){
        return employeeService.getEmployeeByUsername(username);
    }

    @PutMapping("/{username}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editAnEmployee(@PathVariable String username, @RequestBody EmployeeRequest employeeRequest){
        return employeeService.editAnEmployee(username, employeeRequest);
    }


}
