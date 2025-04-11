package com.jbatrina.EmployeeManagementSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
@AttributeOverride(name = "personId", column = @Column(name = "employeeId"))
public class Employee extends Person {
    @NotNull
    private Double salary;
    
    @NotNull
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private EmployeeType employeeType;

    @Override
    public String toString() {
    	return String.format("EMPLOYEE: %s (age %d)", super.toString(), super.getAge());
    }
    
    // manual constructor including Person fields for convenience
    public Employee(
    	// Person fields
		String firstName,
		String lastName,
		String middleName,
		LocalDate dateOfBirth,

		// Employee fields
		Double salary,
		EmployeeType employeeType
    ) {
    	super(firstName, lastName, middleName, dateOfBirth);
    	this.salary = salary;
    	this.employeeType = employeeType;
    }

	public int getEmployeeId() {
		return super.getPersonId();
	}

	public void setEmployeeId(int id) {
		super.setPersonId(id);
	}
}
