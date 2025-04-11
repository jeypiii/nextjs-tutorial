package com.jbatrina.EmployeeManagementSystem.controller;

import com.jbatrina.EmployeeManagementSystem.exceptions.AuthAdminRequiredException;
import com.jbatrina.EmployeeManagementSystem.exceptions.DepartmentIdConflictException;
import com.jbatrina.EmployeeManagementSystem.exceptions.EmployeeIdConflictException;
import com.jbatrina.EmployeeManagementSystem.exceptions.EmployeeImageNotFoundException;
import com.jbatrina.EmployeeManagementSystem.entity.Department;
import com.jbatrina.EmployeeManagementSystem.entity.Employee;
import com.jbatrina.EmployeeManagementSystem.service.AuthService;
import com.jbatrina.EmployeeManagementSystem.service.DepartmentService;
import com.jbatrina.EmployeeManagementSystem.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class DepartmentController extends AdminController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    private AuthService authService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable int id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping("/addDepartment")
    public int addDepartment(@Valid @RequestBody Department department) throws DepartmentIdConflictException {
        requireAdmin();
        Department newDepartment = departmentService.addDepartment(department);
        return newDepartment.getDepartmentId();
    }

    @PutMapping("/updateDepartment/{id}")
    public void updateDepartment(@PathVariable int id, @RequestBody Department department) {
        requireAdmin();
        departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("removeDepartment/{id}")
    public void updateDepartment(@PathVariable int id) {
        requireAdmin();
        departmentService.removeDepartment(id);
    }

}
