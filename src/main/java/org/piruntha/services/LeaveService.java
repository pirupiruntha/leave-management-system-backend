package org.piruntha.services;

import org.piruntha.dto.requests.LeaveRequest;
import org.piruntha.dto.requests.UpdateLeaveRequest;
import org.piruntha.exceptions.CustomException;
import org.piruntha.model.Employee;
import org.piruntha.model.Leave;
import org.piruntha.model.Status;
import org.piruntha.repository.EmployeeRepository;
import org.piruntha.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveService {
    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public Leave addLeave( LeaveRequest leaveRequest, String username){
        Leave leave = new Leave();
        leave.setEmpUsername(username);
        leave.setStartDate(leaveRequest.getStartDate());
        leave.setEndDate(leaveRequest.getEndDate());
        leave.setHalfDay(leaveRequest.isHalfDay());
        leave.setReason(leaveRequest.getReason());
        leave.setStatus(Status.PENDING);

        return leaveRepository.save(leave);
    }

    public List<Leave> getAllLeaves(){

        return leaveRepository.findAll();
    }

    public List<Leave> getLeavesByUsername(String username){

        return leaveRepository.findAllByEmpUsername(username);

    }

    public Leave updateLeave(UpdateLeaveRequest updateLeaveRequest) {
        Leave leave = leaveRepository.findById(updateLeaveRequest.getLeaveId()).orElseThrow(()-> new CustomException("Leave not found", 401));

        leave.setStatus(updateLeaveRequest.getStatus());
        if(updateLeaveRequest.getStatus().equals("APPROVED")){
            Employee employee = employeeRepository.findEmployeeByUsername(leave.getEmpUsername()).orElseThrow(()-> new CustomException("user not found"));
            if (leave.isHalfDay()){
                if(ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate())==1){
                    employee.setLeaveBalance(employee.getLeaveBalance()- 0.5);
                }else{
                    employee.setLeaveBalance(employee.getLeaveBalance()- ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate())+0.5);
                }

            }else {
                employee.setLeaveBalance(employee.getLeaveBalance()- ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()));
            }
            employeeRepository.save(employee);
        }
        return leaveRepository.save(leave);
    }
}
