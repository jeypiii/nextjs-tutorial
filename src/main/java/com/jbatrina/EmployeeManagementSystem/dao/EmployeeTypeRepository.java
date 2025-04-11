package com.jbatrina.EmployeeManagementSystem.dao;

import com.jbatrina.EmployeeManagementSystem.entity.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {
    Optional<EmployeeType> findByName(String employeeTypeName);
}
