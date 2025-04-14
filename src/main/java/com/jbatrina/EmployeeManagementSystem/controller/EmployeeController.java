package com.jbatrina.EmployeeManagementSystem.controller;

import com.jbatrina.EmployeeManagementSystem.exceptions.AuthAdminRequiredException;
import com.jbatrina.EmployeeManagementSystem.exceptions.EmployeeIdConflictException;
import com.jbatrina.EmployeeManagementSystem.exceptions.EmployeeImageNotFoundException;
import com.jbatrina.EmployeeManagementSystem.entity.Employee;
import com.jbatrina.EmployeeManagementSystem.service.AuthService;
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
public class EmployeeController extends AdminController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public void homePage(HttpServletResponse response) {
        response.setHeader("Location", "/employees");
        response.setStatus(HttpStatus.FOUND.value());
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/addEmployee")
    public int addEmployee(@Valid @RequestBody Employee employee) throws EmployeeIdConflictException {
        requireAdmin();
        Employee newEmployee = employeeService.addEmployee(employee);
        return newEmployee.getEmployeeId();
    }

    @PutMapping("/updateEmployee/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        requireAdmin();
        employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/removeEmployee/{id}")
    public void updateEmployee(@PathVariable int id) {
        requireAdmin();
        employeeService.removeEmployee(id);
    }

    @GetMapping("/getEmployeeImage/{employeeId}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getEmployeeImage(@PathVariable int employeeId) {
        MediaType contentType = MediaType.IMAGE_JPEG;
        InputStream in = null;
        try {
            in = new BufferedInputStream(
                    Files.newInputStream(Paths.get(String.format("src/main/resources/static/img/employee%d.jpg", employeeId)))
                );
        } catch (IOException e) {
            throw new EmployeeImageNotFoundException(employeeId);
        }

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(in));
    }

    @PostMapping("/setEmployeeImage/{employeeId}")
    public void setEmployeeImage(@PathVariable int employeeId, @RequestParam("image") MultipartFile file) {
        requireAdmin();

        try {
            Path fileNameAndPath = Paths.get(String.format("src/main/resources/static/img/employee%d.jpg", employeeId));
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new EmployeeImageNotFoundException(employeeId).setContextMessage("Could not create employee image file in the server");
        }
    }

    // Endpoints for processing methods
    @GetMapping("/getAverageSalary")
    public Double getAverageSalary(@RequestBody int[] employeeIds) {
    	if (employeeIds.length == 0) {
			return employeeService.calculateAverageSalary();
    	}
   
    	return employeeService.calculateAverageSalary(employeeIds);
    }

    @GetMapping("/getAverageAge")
    public Double getAverageAge(@RequestBody int[] employeeIds) {
    	if (employeeIds.length == 0) {
			return employeeService.calculateAverageAge();
    	}
   
    	return employeeService.calculateAverageAge(employeeIds);
    }

}
