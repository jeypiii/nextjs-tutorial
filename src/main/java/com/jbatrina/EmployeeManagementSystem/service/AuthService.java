package com.jbatrina.EmployeeManagementSystem.service;

import com.jbatrina.EmployeeManagementSystem.dto.LoginDto;
import com.jbatrina.EmployeeManagementSystem.entity.User;

public interface AuthService {
    String login(LoginDto loginDto);
    public User getCurrentUser();

    boolean isAdmin();
}