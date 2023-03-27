package org.piruntha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private int empId;
    private String fullName;
    private Date dob;
    private String gender;
    private String education;
    private String jobTitle;
    private Date startDate;
    private double salary;

}
