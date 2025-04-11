package com.jbatrina.EmployeeManagementSystem.exceptions;

import lombok.Getter;
import lombok.Setter;

import com.jbatrina.EmployeeManagementSystem.entity.Department;
import com.jbatrina.EmployeeManagementSystem.entity.Employee;

import org.springframework.http.HttpStatus;

@Getter
public class DepartmentDoesNotContainEmployeeException extends DepartmentException {
    private static final long serialVersionUID = 1L;

    private Employee employee;

    public DepartmentDoesNotContainEmployeeException(int id, String message, Department Department) {
        super("The Department does not have the employee with the given ID", id, message, Department);
        this.setHttpStatus(HttpStatus.CONFLICT);
    }

    public DepartmentDoesNotContainEmployeeException(int id) {
        this(id, "", null);
    }
    
    public DepartmentDoesNotContainEmployeeException setEmployee(Employee employee) {
    	this.employee = employee;
    	return this;
    }
}
