package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Employee;
import org.springframework.http.HttpStatus;

public class EmployeeImageNotFoundException extends EmployeeException {
    private static final long serialVersionUID = 1L;

    public EmployeeImageNotFoundException(int id, String message, Employee employee) {
        super("The employee has no corresponding image", id, message, employee);
        this.setHttpStatus(HttpStatus.NOT_FOUND);
    }

    public EmployeeImageNotFoundException(int id) {
        this(id, "", null);
    }
}
