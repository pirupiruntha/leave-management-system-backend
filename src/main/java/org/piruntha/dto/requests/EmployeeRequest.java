package org.piruntha.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.piruntha.model.Gender;
import org.piruntha.model.JobTitle;
import org.piruntha.model.UserRoles;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeRequest {
    @NotBlank
    private String empId;
    @NotBlank
    private String fullName;
    private LocalDate dob;
    private Gender gender;
    @NotBlank
    private String email;
    private String telephoneNo;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String education;
    private JobTitle jobTitle;
    private int leaveAllowance;
    private LocalDate startDate;
    private double salary;
    private List<UserRoles> roles;
}
