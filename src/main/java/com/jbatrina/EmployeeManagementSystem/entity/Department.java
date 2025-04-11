package com.jbatrina.EmployeeManagementSystem.entity;

import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int departmentId;
    
    @NotEmpty
    @Column(nullable = false)
    private String name;
    
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "department_employees",
            joinColumns = @JoinColumn(name = "depatrment_id", referencedColumnName = "departmentId"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    )
    private Set<Employee> employees;
    

    // convenience constructor
    public Department(String name) {
    	this.name = name;
	}

}
