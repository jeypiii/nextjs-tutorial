package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Department;
import org.springframework.http.HttpStatus;

public class DepartmentIdConflictException extends DepartmentException {
    private static final long serialVersionUID = 1L;

    public DepartmentIdConflictException(int id, String message, Department Department) {
        super("An Department with the same ID already exists.", id, message, Department);
        this.setHttpStatus(HttpStatus.CONFLICT);
    }

    public DepartmentIdConflictException(int id) {
        this(id, "", null);
    }
}
