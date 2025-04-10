package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.User;

public class UserException extends EmployeeManagementSystemException {
    protected UserException(String baseMessage, int id, String message, Object contextObject) {
        super(baseMessage, id, message, contextObject);
    }

    public UserException(int id, String message, User user) {
        this("A user-related error has occurred.", id, message, (Object) user);
    }

    public UserException(int id, String message) {
        this(id, message, null);
    }

    public UserException(int id) {
        this(id, "", null);
    }

    public User getUser() {
        return (User) getContextObject();
    }

    public UserException setUser(User user) {
        setContextObject((Object) user);
        return this;
    }
}
