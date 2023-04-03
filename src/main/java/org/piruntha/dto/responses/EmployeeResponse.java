package org.piruntha.dto.responses;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeResponse {
    private int empId;
    private String fullName;
    private Date dob;
    private String gender;
    private String email;
    private String telephoneNo;
    private String education;
    private String jobTitle;
    private Date startDate;
    private double salary;
}
