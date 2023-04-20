package org.piruntha.dto.responses;

import lombok.Data;
import org.piruntha.model.Gender;
import org.piruntha.model.JobTitle;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeResponse {
    private String empId;
    private String fullName;
    private LocalDate dob;
    private Gender gender;
    private String email;
    private String telephoneNo;
    private String education;
    private JobTitle jobTitle;
    private LocalDate startDate;
    private double salary;
}
