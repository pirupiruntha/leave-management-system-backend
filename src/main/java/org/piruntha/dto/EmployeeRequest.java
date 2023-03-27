package org.piruntha.dto;

import lombok.Data;

import java.util.Date;
@Data
public class EmployeeRequest {
    private int empId;
    private String fullName;
    private Date dob;
    private String gender;
    private String education;
    private String jobTitle;
    private Date startDate;
    private double salary;
}
