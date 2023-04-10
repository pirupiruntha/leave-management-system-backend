package org.piruntha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employees")
public class Employee {

    private int empId;
    private String fullName;
    private Date dob;
    private String email;
    @Id
    private String username;
    private String password;
    private List<UserRoles> roles;
    private String gender;
    private String telephoneNo;
    private double leaveAllowance;
    private double leaveBalance;
    private String education;
    private String jobTitle;
    private Date startDate;
    private double salary;

}
