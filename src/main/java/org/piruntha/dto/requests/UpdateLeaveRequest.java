package org.piruntha.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.piruntha.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLeaveRequest {
    private String leaveId;
    private Status status;

}
