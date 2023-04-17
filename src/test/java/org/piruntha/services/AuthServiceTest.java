package org.piruntha.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.piruntha.dto.requests.AuthRequest;
import org.piruntha.dto.responses.LoginResponse;
import org.piruntha.jwt.JwtService;
import org.piruntha.model.Employee;
import org.piruntha.model.UserRoles;
import org.piruntha.repository.EmployeeRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void testAuthenticateUser() {
        // Mock input
        String username = "testuser";
        String password = "testpassword";
        AuthRequest authRequest = new AuthRequest(username, password);

        // Mock authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        // Mock repository
        List<UserRoles> roles = new ArrayList<>();
        roles.add(UserRoles.ROLE_USER);
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(passwordEncoder.encode(password));
        employee.setRoles(roles);
        Optional<Employee> employeeOptional = Optional.of(employee);
        when(employeeRepository.findEmployeeByUsername(username))
                .thenReturn(employeeOptional);

        // Mock JWT service
        String token = "testtoken";
        when(jwtService.generateToken(username)).thenReturn(token);

        // Test authentication
        LoginResponse loginResponse = authService.authenticateUser(authRequest);

        // Assert the response
        Assertions.assertNotNull(loginResponse);
        Assertions.assertEquals(token, loginResponse.getToken());
        Assertions.assertEquals(roles, loginResponse.getRoles());
    }

    @Test
    void testInvalidUserRequest() {
        // Mock input
        String username = "testuser";
        String password = "testpassword";
        AuthRequest authRequest = new AuthRequest(username, password);

        // Mock authentication
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new UsernameNotFoundException("Invalid user request ! "));

        // Test authentication
        Assertions.assertThrows(UsernameNotFoundException.class, () -> authService.authenticateUser(authRequest));
    }

}
