package org.piruntha.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.piruntha.dto.requests.EmployeeRequest;
import org.piruntha.dto.responses.DeleteResponse;
import org.piruntha.dto.responses.EmployeeResponse;
import org.piruntha.exceptions.EmailExistsException;
import org.piruntha.model.Employee;
import org.piruntha.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeRequest employeeRequest){
        try {
            Employee createdEmployee = employeeService.addEmployee(employeeRequest);
            return ResponseEntity.ok(createdEmployee);
        } catch (EmailExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Employee> getAllEmployees(EmployeeResponse employeeResponse){
        List<Employee> employees = employeeService.getEmployees(employeeResponse);
        return employees;
    }

    @GetMapping("/{username}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public Optional<Employee> getAnEmployeeByUsername(@PathVariable String username){
        return employeeService.getEmployeeByUsername(username);
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Employee editAnEmployee(@PathVariable String username, @RequestBody EmployeeRequest employeeRequest){
        return employeeService.editAnEmployee(username, employeeRequest);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public DeleteResponse deleteAnEmployee(@PathVariable String username){
        return employeeService.deleteEmployee(username);
    }

}
