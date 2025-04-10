package com.jbatrina.EmployeeManagementSystem.service;

import com.jbatrina.EmployeeManagementSystem.dao.UserRepository;
import com.jbatrina.EmployeeManagementSystem.exceptions.AuthUserExistsException;
import com.jbatrina.EmployeeManagementSystem.exceptions.UserInvalidEmailException;
import com.jbatrina.EmployeeManagementSystem.exceptions.UserNotFoundException;
import com.jbatrina.EmployeeManagementSystem.entity.User;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    protected User addUser(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // HACK: spring.security exception not specific enough, need to check message
            // TODO: Update this whenever spring's exception message changes (e.g. i18n??)
            if (e.getMessage().contains("Duplicate")) {
                String message;
                if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                    message = "The username is already taken.";
                } else {
                    message = "The email is already associated with another account.";
                }
                throw new AuthUserExistsException(user).setContextMessage(message);
            }
        } catch (ConstraintViolationException e) {
            // HACK: ConstraintViolationException not specific enough, need to check message
            // TODO: Update this whenever Jakarta validation's exception message changes (e.g. i18n??)
            if (e.getMessage().toLowerCase().contains("email is not valid")) {
                throw new UserInvalidEmailException(user.getUserId()).setContextMessage("The provided email is invalid.");
            }

        }

        return user;
    }

    public User addAdminUser(User user, String password) {
        user.setRoles(Set.of(roleService.getAdminRole()));
        return addUser(user, password);
    }

    public User addNormalUser(User user, String password) {
        user.setRoles(Set.of(roleService.getUserRole()));
        return addUser(user, password);
    }

    public User getById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId).setContextMessage("No User with Id " + userId));

        return user;
    }
}
