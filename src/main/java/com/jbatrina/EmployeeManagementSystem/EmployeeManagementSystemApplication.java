package com.jbatrina.EmployeeManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jbatrina.EmployeeManagementSystem.entity.Role;
import com.jbatrina.EmployeeManagementSystem.entity.User;
import com.jbatrina.EmployeeManagementSystem.service.RoleService;
import com.jbatrina.EmployeeManagementSystem.service.UserService;
import java.util.Set;

@SpringBootApplication
public class EmployeeManagementSystemApplication implements CommandLineRunner {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

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
