package org.piruntha.config;

import org.piruntha.model.Employee;
import org.piruntha.model.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(Employee employee){
        username = employee.getUsername();
        password = employee.getPassword();
        authorities = employee.getRoles().stream()
                .map(UserRoles::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

//    public UserInfoUserDetails(UserInfo userInfo){
//        username = userInfo.getName();
//        password = userInfo.getPassword();
//        authorities = Arrays.stream(userInfo.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
