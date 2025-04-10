package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Employee;
import org.springframework.http.HttpStatus;

public class EmployeeIdConflictException extends EmployeeException {
    private static final long serialVersionUID = 1L;

    public EmployeeIdConflictException(int id, String message, Employee Employee) {
        super("An Employee with the same ID already exists.", id, message, Employee);
        this.setHttpStatus(HttpStatus.CONFLICT);
    }

    public EmployeeIdConflictException(int id) {
        this(id, "", null);
    }
}
