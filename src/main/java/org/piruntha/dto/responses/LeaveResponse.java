package org.piruntha.dto.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Data
public class LeaveResponse {
    private String username;
    private String id;
    private String empUsername;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private boolean halfDay;
    private String status;
}
