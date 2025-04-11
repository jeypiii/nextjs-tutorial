package com.jbatrina.EmployeeManagementSystem.dao;

import com.jbatrina.EmployeeManagementSystem.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
