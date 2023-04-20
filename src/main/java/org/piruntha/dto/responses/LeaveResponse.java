package org.piruntha.dto.responses;

import lombok.Data;
import org.piruntha.model.Status;

import java.time.LocalDate;
import java.util.List;

@Data
public class LeaveResponse {
    private String empId;
    private String id;
    private String empUsername;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean halfDay;
    private Status status;
}
