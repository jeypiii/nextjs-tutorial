package com.jbatrina.EmployeeManagementSystem.entity;

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

    @Override
    public String toString() {
    	return String.format("EMPLOYEE: %s (age %d)", super.toString(), super.getAge());
    }
    
	public int getEmployeeId() {
		return super.getPersonId();
	}

	public void setEmployeeId(int id) {
		super.setPersonId(id);
	}
}
