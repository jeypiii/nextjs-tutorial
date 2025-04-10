package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Employee;

public class EmployeeException extends EmployeeManagementSystemException{
    protected EmployeeException(String baseMessage, int id, String message, Object contextObject) {
        super(baseMessage, id, message, contextObject);
    }

    public EmployeeException(int id, String message, Employee employee) {
        this("An employee-related error has occurred.", id, message, (Object) employee);
    }

    public EmployeeException(int id, String message) {
        this(id, message, null);
    }

    public EmployeeException(int id) {
        this(id, "", null);
    }

    public Employee getEmployee() {
        return (Employee) getContextObject();
    }

    public com.jbatrina.EmployeeManagementSystem.exceptions.EmployeeException setEmployee(Employee employee) {
        setContextObject((Object) employee);
        return this;
    }
}
