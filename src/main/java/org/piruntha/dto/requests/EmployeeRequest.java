package org.piruntha.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.piruntha.model.UserRoles;

import java.util.Date;
import java.util.List;

@Data
public class EmployeeRequest {
    @NotBlank
    private int empId;
    @NotBlank
    private String fullName;
    private Date dob;
    private String gender;
    @NotBlank
    private String email;
    private String telephoneNo;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String education;
    private String jobTitle;
    private int leaveAllowance;
    private Date startDate;
    private double salary;
    private List<UserRoles> roles;
}
