package org.piruntha.controllers;

import org.piruntha.dto.requests.LeaveRequest;
import org.piruntha.model.Leave;
import org.piruntha.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/leave/{username}")
    public String addLeave(@RequestBody LeaveRequest leaveRequest, @PathVariable String username){
        return leaveService.addLeave(leaveRequest, username);

    }
    @GetMapping("/leaves")
    public List<Leave> getAllLeaveRequests(){
        return leaveService.getAllLeaves();

    }
    @GetMapping("/leave/{username}")
    public List<Leave> getLeavesByUsername(@PathVariable String username){
        return leaveService.getLeavesByUsername(username);

    }

}
