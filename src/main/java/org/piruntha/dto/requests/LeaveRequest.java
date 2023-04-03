package org.piruntha.dto.requests;

import lombok.Data;

import java.util.Date;
@Data
public class LeaveRequest {
    private String empUsername;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private boolean halfDay;
    private  String reason;
}
