package com.demo.controller;
  
import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/employee/")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Employee employee) {
        return "add-employee";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("employee", employeeRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addEmployee( Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-employee";
        }

        employeeRepository.save(employee);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
    	Employee employee= employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("update/{id}")
    public String updateEmployee(@PathVariable("id") int id, Employee employee, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
        	employee.setId(id);
            return "update-employee";
        }

        employeeRepository.save(employee);
        model.addAttribute("employee", employeeRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model) {
    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
    employeeRepository.delete(employee);
        model.addAttribute("employee", employeeRepository.findAll());
        return "index";
    }
}