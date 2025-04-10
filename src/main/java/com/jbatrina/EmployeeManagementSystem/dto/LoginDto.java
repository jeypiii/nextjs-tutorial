package com.jbatrina.EmployeeManagementSystem.dto;

import com.jbatrina.EmployeeManagementSystem.entity.User;

public class LoginDto {
    private String usernameOrEmail;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public LoginDto(User user) {
        this(
                (! user.getUsername().isBlank()) ? user.getUsername() : user.getEmail()
                , user.getPassword()
        );
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
