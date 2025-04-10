package com.jbatrina.EmployeeManagementSystem.entity;

import java.time.LocalDate;

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
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int personId;
    
    // name
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Column(nullable = true)
    private String middleName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;
    
    public int getAge() {
    	LocalDate ageDate = LocalDate.now();
    	ageDate.minusYears(dateOfBirth.getYear());
    	ageDate.minusMonths(dateOfBirth.getMonthValue());
    	ageDate.minusDays(dateOfBirth.getDayOfYear());
    	
    	return ageDate.getYear();
    }
}
