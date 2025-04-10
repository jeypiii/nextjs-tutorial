package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.User;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(int id, String message, User user) {
        super("The user requested does not exist.", id, message, user);
        this.setHttpStatus(HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(int id) {
        this(id, "", null);
    }
}
