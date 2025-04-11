package com.jbatrina.EmployeeManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.jbatrina.EmployeeManagementSystem.exceptions.AuthAdminRequiredException;
import com.jbatrina.EmployeeManagementSystem.service.AuthService;
import com.jbatrina.EmployeeManagementSystem.service.EmployeeService;

public abstract class AdminController {
    @Autowired
    public EmployeeService employeeService;
    @Autowired
    public AuthService authService;
 
    protected void requireAdmin() {
        if (!authService.isAdmin()) {
            throw new AuthAdminRequiredException()
                    .setContextMessage("Attempting to use an admin-only api");
        }
    }
}