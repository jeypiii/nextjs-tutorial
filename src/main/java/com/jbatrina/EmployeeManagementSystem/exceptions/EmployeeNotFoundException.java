package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Employee;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

public class EmployeeNotFoundException extends EmployeeException {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(int id, String message, Employee Employee) {
        super("The Employee requested does not exist.", id, message, Employee);
        this.setHttpStatus(HttpStatus.NOT_FOUND);
    }

    public EmployeeNotFoundException(int id) {
        this(id, "", null);
    }
}
