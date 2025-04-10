package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Employee;
import org.springframework.http.HttpStatus;

public class EmployeeIdMismatchException extends EmployeeException {
    private static final long serialVersionUID = 1L;

    public EmployeeIdMismatchException(int id, String message, Employee Employee) {
        super("The supplied ID does not match the Employee's ID .", id, message, Employee);
        this.setHttpStatus(HttpStatus.CONFLICT);
    }

    public EmployeeIdMismatchException(int id) {
        this(id, "", null);
    }
}
