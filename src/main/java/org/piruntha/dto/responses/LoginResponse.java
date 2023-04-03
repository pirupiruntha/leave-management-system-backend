package org.piruntha.dto.responses;

import lombok.Getter;
import lombok.Setter;
import org.piruntha.model.UserRoles;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Getter
@Setter
public class LoginResponse {
    String token;
    List<UserRoles> roles;
}
