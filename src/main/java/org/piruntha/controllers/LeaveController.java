package org.piruntha.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.piruntha.dto.requests.LeaveRequest;
import org.piruntha.dto.requests.UpdateLeaveRequest;
import org.piruntha.model.Leave;
import org.piruntha.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/employee")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @PostMapping("/leave/{username}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Leave addLeave(@RequestBody LeaveRequest leaveRequest, @PathVariable String username){
        return leaveService.addLeave(leaveRequest, username);
    }

    @GetMapping("/leaves")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Leave> getAllLeaveRequests(){
        return leaveService.getAllLeaves();
    }

    @GetMapping("/leave/{username}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public List<Leave> getLeavesByUsername(@PathVariable String username){
        return leaveService.getLeavesByUsername(username);
    }

    @PutMapping("/leave")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public Leave updateLeave( @RequestBody UpdateLeaveRequest updateLeaveRequest){
        return leaveService.updateLeave( updateLeaveRequest);
    }
}
