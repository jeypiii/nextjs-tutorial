package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Department;
import org.springframework.http.HttpStatus;

public class DepartmentAlreadyContainsEmployeeException extends DepartmentException {
    private static final long serialVersionUID = 1L;

    public DepartmentAlreadyContainsEmployeeException(int id, String message, Department Department) {
        super("An employee the same ID already exists in this department.", id, message, Department);
        this.setHttpStatus(HttpStatus.CONFLICT);
    }

    public  DepartmentAlreadyContainsEmployeeException(int id) {
        this(id, "", null);
    }
}
