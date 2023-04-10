package org.piruntha.services;

import org.piruntha.dto.requests.AuthRequest;
import org.piruntha.dto.responses.LoginResponse;
import org.piruntha.jwt.JwtService;
import org.piruntha.model.Employee;
import org.piruntha.model.UserRoles;
import org.piruntha.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse authenticateUser(AuthRequest authRequest) {
        //authenticate the user
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()){
            //generate JWT
            String token = jwtService.generateToken(authRequest.getUsername());
            //get user from DB
            Optional<Employee> user = employeeRepository.findEmployeeByUsername(authRequest.getUsername());

            //set user roles to a list from user retrieved from DB
            List<UserRoles> role = user.get().getRoles();
            //create a new LoginResponse object with the generated token and role list
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setRoles(role);
            return loginResponse;
        }else {
            throw new UsernameNotFoundException("Invalid user request ! ");
        }
    }
}
