package com.jbatrina.EmployeeManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jbatrina.EmployeeManagementSystem.entity.Employee;
import com.jbatrina.EmployeeManagementSystem.entity.EmployeeType;
import com.jbatrina.EmployeeManagementSystem.entity.Role;
import com.jbatrina.EmployeeManagementSystem.entity.User;
import com.jbatrina.EmployeeManagementSystem.service.EmployeeService;
import com.jbatrina.EmployeeManagementSystem.service.EmployeeTypeService;
import com.jbatrina.EmployeeManagementSystem.service.RoleService;
import com.jbatrina.EmployeeManagementSystem.service.UserService;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class EmployeeManagementSystemApplication implements CommandLineRunner {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeTypeService employeeTypeService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// NOTE: database is re-created each run (via ddl-auto)

		System.out.println("DDL AUTO" + ddlAuto);
		if (ddlAuto != null && !ddlAuto.isBlank()) {
			// We add dummy values to populate database
			// add roles and users

			// TODO: add roles and users to permanent storage
			Role adminRole = roleService.addRole(new Role("ADMIN"));
			Role userRole = roleService.addRole(new Role("USER"));
			
			// TODO: add employees and types to permanent storage
			EmployeeType probationaryType = employeeTypeService.addEmployeeType("PROBATIONARY");
			EmployeeType regularType = employeeTypeService.addEmployeeType("REGULAR");
			EmployeeType contractualType = employeeTypeService.addEmployeeType("CONTRACTUAL");
			
			employeeService.addEmployee(new Employee(
						"proby",
						"probationary",
						"probo",
						LocalDate.of(2000, 2, 20),
						
						180000.0,
						probationaryType
					));

			employeeService.addEmployee(new Employee(
						"reggie",
						"regular",
						"regulo",
						LocalDate.of(1990, 9, 19),
						
						360000.0,
						regularType
					));

			employeeService.addEmployee(new Employee(
						"conty",
						"contractual",
						"contro",
						LocalDate.of(2005, 5, 15),
						
						156000.0,
						contractualType
					));
			
			
			// add users
			userService.addAdminUser(new User(
					"admin",
					"admin@admin.com",
					"user",	// password provided as separate arg
					Set.of(adminRole)),
					"admin"
			);

			userService.addNormalUser(new User(
					"user",
					"user@user.com",
					"user",	// password provided as separate arg
					Set.of(userRole)),
					"user"
			);
		}
	}
}
