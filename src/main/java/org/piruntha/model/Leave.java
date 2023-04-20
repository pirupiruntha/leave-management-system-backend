package org.piruntha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "leave")
public class Leave {
    @Id
    private String id;
    private String empUsername;
    private String empId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean halfDay;
    private  String reason;
    private Status status;

    public void setEmpUsername(String empUsername) {
        this.empUsername = empUsername;
    }

}
