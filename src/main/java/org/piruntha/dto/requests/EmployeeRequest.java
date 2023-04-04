package org.piruntha.dto.requests;

import lombok.Data;
import org.piruntha.model.UserRoles;

import java.util.Date;
import java.util.List;

@Data
public class EmployeeRequest {
    private int empId;
    private String fullName;
    private Date dob;
    private String gender;
    private String email;
    private String telephoneNo;
    private String username;
    private String password;
    private String education;
    private String jobTitle;
    private Date startDate;
    private double salary;
    private List<UserRoles> roles;
}
