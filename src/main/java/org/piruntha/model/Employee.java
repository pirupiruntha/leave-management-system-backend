package org.piruntha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee")
public class Employee {
    @Indexed(unique = true)
    private String empId;
    private String fullName;
    private LocalDate dob;
    @Indexed(unique = true)
    private String email;
    @Id
    private String username;
    private String password;
    private List<UserRoles> roles;
    private Gender gender;
    @Indexed(unique = true)
    private String telephoneNo;
    private double leaveAllowance;
    private double leaveBalance;
    private String education;
    private JobTitle jobTitle;
    private LocalDate startDate;
    private double salary;

}
