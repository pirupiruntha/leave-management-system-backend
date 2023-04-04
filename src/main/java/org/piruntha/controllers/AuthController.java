package org.piruntha.controllers;

import org.piruntha.dto.requests.AuthRequest;
import org.piruntha.dto.responses.LoginResponse;
import org.piruntha.jwt.JwtService;
import org.piruntha.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Call service method - UserInfoService
    //Method to generate token and return token and list of roles
    //LoginResponse loginResponse = userInfoService.authenticateUser(authRequest)
    @PostMapping("/login")
    public LoginResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        LoginResponse loginResponse = authService.authenticateUser(authRequest);
        return loginResponse;
    }
}
