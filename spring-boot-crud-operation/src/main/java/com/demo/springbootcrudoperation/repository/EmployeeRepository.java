package com.demo.springbootcrudoperation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.springbootcrudoperation.model.Employee;

//repository that extends CrudRepository  
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	public List<Employee> findByAge(int age);
}
