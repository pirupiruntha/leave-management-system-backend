package org.piruntha.services;

import org.piruntha.dto.requests.LeaveRequest;
import org.piruntha.model.Leave;
import org.piruntha.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    @Autowired
    LeaveRepository leaveRepository;
    public String addLeave( LeaveRequest leaveRequest, String username){
        Leave leave = new Leave();
        leave.setLeaveType(leaveRequest.getLeaveType());
        leave.setEmpUsername(username);
        leave.setStartDate(leaveRequest.getStartDate());
        leave.setEndDate(leaveRequest.getEndDate());
        leave.setReason(leaveRequest.getReason());
        leave.setStatus("Pending");
        leaveRepository.save(leave);
        return "Successfully sent your leave request";
    }

    public List<Leave> getAllLeaves(){

        return leaveRepository.findAll();
    }

    public List<Leave> getLeavesByUsername(String username){

        return leaveRepository.findAllByEmpUsername(username);

    }


}
