package org.piruntha.dto.requests;

import lombok.Data;

import java.time.LocalDate;
@Data
public class LeaveRequest {
    private String empUsername;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean halfDay;
    private  String reason;
}
