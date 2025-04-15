package com.jbatrina.EmployeeManagementSystem.dao;

import com.jbatrina.EmployeeManagementSystem.entity.Department;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

	List<Department> findAllByEmployeesPersonId(int id);
}
