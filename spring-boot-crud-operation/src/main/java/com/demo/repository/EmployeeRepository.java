package com.demo.repository;
  
import java.util.List;

import org.springframework.data.repository.CrudRepository;  
import com.demo.model.Employee;  
//repository that extends CrudRepository  
public interface EmployeeRepository extends CrudRepository<Employee, Integer>  
{ 
	public List<Employee>  FindByAge(int Age);
}  
