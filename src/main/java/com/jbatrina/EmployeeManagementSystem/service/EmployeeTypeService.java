package com.jbatrina.EmployeeManagementSystem.service;

import com.jbatrina.EmployeeManagementSystem.dao.EmployeeTypeRepository;
import com.jbatrina.EmployeeManagementSystem.entity.EmployeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeTypeService {
    @Autowired
    EmployeeTypeRepository employeeTypeRepository;

    public EmployeeType addEmployeeType(EmployeeType employeeType) {
        Optional<EmployeeType> oldEmployeeType = employeeTypeRepository.findByName(employeeType.getName());
        if (oldEmployeeType.isPresent()) {
            // TODO: check if it's better to raise error here than silent ack of re-add attempt
            return oldEmployeeType.get();
        }

        employeeTypeRepository.save(employeeType);
        return employeeType;
    }

    public EmployeeType addEmployeeType(String name) {
    	return addEmployeeType(new EmployeeType(name));
    }

    public Optional<EmployeeType> getEmployeeType(String name) {
        return employeeTypeRepository.findByName(name);
    }

    public EmployeeType getAdminEmployeeType() {
        // TODO: proper exception
        return getEmployeeType("ADMIN").orElseThrow(() -> new RuntimeException("Admin employeeType not initialized"));
    }

    public EmployeeType getUserEmployeeType() {
        // TODO: proper exception
        return getEmployeeType("USER").orElseThrow(() -> new RuntimeException("Admin employeeType not initialized"));
    }
}
