package com.jbatrina.EmployeeManagementSystem.service;

import com.jbatrina.EmployeeManagementSystem.dao.DepartmentRepository;
import com.jbatrina.EmployeeManagementSystem.dao.EmployeeRepository;
import com.jbatrina.EmployeeManagementSystem.entity.Department;
import com.jbatrina.EmployeeManagementSystem.entity.Employee;
import com.jbatrina.EmployeeManagementSystem.exceptions.EmployeeIdConflictException;
import com.jbatrina.EmployeeManagementSystem.exceptions.EmployeeIdMismatchException;
import com.jbatrina.EmployeeManagementSystem.exceptions.EmployeeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);

        return employees;
    }

    public Employee getEmployee(int id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException(id).setContextMessage("Attempting to get employee by Id");
        }

        return employee.get();
    }

    public Employee addEmployee(Employee employee) throws EmployeeIdConflictException {
        // NOTE: For requests the body, don't include employeeId in body since it is autogenerated
        if (employeeRepository.findById(employee.getEmployeeId()).isPresent()) {
            throw new EmployeeIdConflictException(employee.getEmployeeId()).setContextMessage("Attempting to add " + employee);
        }

        return employeeRepository.save(employee);
    }

    public void updateEmployee(int id, Employee employee) {
        final String additionalMessage = "Attempting to update " + employee;

        Employee origEmployee = employeeRepository.findById(id).orElse(null);
        if (origEmployee == null) {
            throw new EmployeeNotFoundException(employee.getEmployeeId()).setContextMessage(additionalMessage);
        }
        
//        if (employee.getEmployeeId() != id) {
//            throw new EmployeeIdMismatchException(employee.getEmployeeId()).setContextMessage(additionalMessage);
//        }
        if (employee.getMiddleName() != null) {
        	String middleName = employee.getMiddleName();
        	if (employee.getMiddleName().isBlank()) {
        		middleName = null;
        	}
        	
        	System.out.println(origEmployee + " mid " + middleName);

			origEmployee.setMiddleName(middleName);
        }
        
        if (! (employee.getFirstName() == null || employee.getFirstName().isBlank())) {
			origEmployee.setFirstName(employee.getFirstName());
        }
        if (! (employee.getLastName() == null || employee.getLastName().isBlank())) {
			origEmployee.setLastName(employee.getLastName());
        }
        if (! (employee.getDateOfBirth() == null)) {
			origEmployee.setDateOfBirth(employee.getDateOfBirth());
        }
        if (! (employee.getSalary() == null)) {
			origEmployee.setSalary(employee.getSalary());
        }
        if (! (employee.getEmployeeType() == null)) {
			origEmployee.setEmployeeType(employee.getEmployeeType());
        }

        employeeRepository.save(origEmployee);
    }

    public void removeEmployees(int[] ids) {
    	for (int id : ids) {
    		removeEmployee(id);
    	}
    }
 
    public void removeEmployee(int id) {
        if (!employeeRepository.findById(id).isPresent()) {
            throw new EmployeeNotFoundException(id).setContextMessage("Attempting to remove employee ");
        }
        
        Employee employee = getEmployee(id);
        
        final List<Department> affectedDepts = departmentRepository.findAllByEmployeesPersonId(id);
        affectedDepts.forEach((dept) -> {
        	dept.getEmployees().remove(employee);
        	departmentRepository.save(dept);
        });

        employeeRepository.deleteById(id);
    }
    
    public List<Employee> getEmployeesById(int [] employeeIds) {
    	List<Employee> employees = new ArrayList<Employee>();
    	for (int employeeId : employeeIds) {
    		employees.add(getEmployee(employeeId));
    	}
    	
    	return employees;
    }
    
    
    // Processing methods
    public double calculateAverageSalary() {
    	return calculateAverageSalary(getAllEmployees());
    }

    public double calculateAverageSalary(int[] employeeIds) {
    	return calculateAverageSalary(getEmployeesById(employeeIds));
    }
 
    public double calculateAverageSalary(List<Employee> employees) {
    	Double totalSalary = 0.0;
    	for (Employee e : employees) {
    		totalSalary += e.getSalary(); 
		}
    	
    	return totalSalary / employees.size();
    }

    public double calculateAverageAge() {
    	return calculateAverageAge(getAllEmployees());
    }

    public double calculateAverageAge(int[] employeeIds) {
    	return calculateAverageAge(getEmployeesById(employeeIds));
    }

    public double calculateAverageAge(List<Employee> employees) {
    	Double totalAge = 0.0;
    	for (Employee e : employees) {
    		System.out.println(e + " Age: " + e.getAge());
    		totalAge += e.getAge(); 
		}
    	
    	return totalAge / employees.size();
    }
    
}

