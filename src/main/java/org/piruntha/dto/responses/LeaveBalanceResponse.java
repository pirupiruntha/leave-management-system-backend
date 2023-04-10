package org.piruntha.dto.responses;

import lombok.Data;

@Data
public class LeaveBalanceResponse {
    private double totalLeaveDays;
    private double balanceLeaveDays;
}
