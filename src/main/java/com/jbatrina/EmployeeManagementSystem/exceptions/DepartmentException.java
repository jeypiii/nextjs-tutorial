package com.jbatrina.EmployeeManagementSystem.exceptions;

import com.jbatrina.EmployeeManagementSystem.entity.Department;

public class DepartmentException extends EmployeeManagementSystemException{
    protected DepartmentException(String baseMessage, int id, String message, Object contextObject) {
        super(baseMessage, id, message, contextObject);
    }

    public DepartmentException(int id, String message, Department department) {
        this("A department-related error has occurred.", id, message, (Object) department);
    }

    public DepartmentException(int id, String message) {
        this(id, message, null);
    }

    public DepartmentException(int id) {
        this(id, "", null);
    }

    public Department getDepartment() {
        return (Department) getContextObject();
    }

    public DepartmentException setDepartment(Department department) {
        setContextObject((Object) department);
        return this;
    }
}
