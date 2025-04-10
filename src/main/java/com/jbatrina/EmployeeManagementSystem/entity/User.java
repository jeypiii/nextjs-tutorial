package com.jbatrina.EmployeeManagementSystem.entity;

import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int userId;

    // login details
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Column(nullable = false)
    private String password;

//    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JoinTable(name = "user_employees",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
//            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
//    )
//    private Employee accountForEmployee;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    )
    private Set<Role> roles;
}
