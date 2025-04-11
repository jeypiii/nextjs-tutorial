package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Department;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

public class DepartmentNotFoundException extends DepartmentException {
    private static final long serialVersionUID = 1L;

    public DepartmentNotFoundException(int id, String message, Department Department) {
        super("The Department requested does not exist.", id, message, Department);
        this.setHttpStatus(HttpStatus.NOT_FOUND);
    }

    public DepartmentNotFoundException(int id) {
        this(id, "", null);
    }
}
