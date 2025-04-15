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

    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    // Manual constructor without Id (autogenerated)
    public Person(
		String firstName,
		String lastName,
		String middleName,
		LocalDate dateOfBirth
    ) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.middleName = middleName;
    	this.dateOfBirth = dateOfBirth;
    }
    
    public int getAge() {
    	LocalDate now = LocalDate.now();
    	LocalDate ageDate = now.minusYears(dateOfBirth.getYear())
    		.minusMonths(dateOfBirth.getMonthValue())
    		.minusDays(dateOfBirth.getDayOfYear());

    	return ageDate.getYear();
    }
    
    @Override
    public String toString() {
    	final boolean hasMiddleName = middleName != null && !middleName.isBlank();

    	return String.format("%s, %s%s", lastName.toUpperCase(), firstName, hasMiddleName ? (" " + middleName) : "");
    }
}
