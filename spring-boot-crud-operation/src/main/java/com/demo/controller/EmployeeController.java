package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;

//mark class as Controller   
@RestController
public class EmployeeController {
//autowire the employeeService class  
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employee")
	private List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("/employee/{age}")
	public List<Employee> getByAge(@PathVariable("age") int age) {
        return employeeService.getEmployeeByAge(age);
    }

//creating a delete mapping that deletes a specified employee 
	@DeleteMapping("/employee/{id}")
	private void deleteEmployee(@PathVariable("id") int id) {
		employeeService.delete(id);
	}

//creating post mapping that post the employee detail in the database  
	@PostMapping("/employee")
	private String saveEmployee(@RequestBody Employee employee) {
		employeeService.saveOrUpdate(employee);
		return employee.getname();
	}

//creating put mapping that updates the employee detail   
	@PutMapping("/employee")
	private Employee update(@RequestBody Employee employee) {
		employeeService.saveOrUpdate(employee);
		return employee;
	}
}