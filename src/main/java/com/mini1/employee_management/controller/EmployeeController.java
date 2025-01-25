package com.mini1.employee_management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini1.employee_management.model.Employee;
import com.mini1.employee_management.respository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		Employee savedEmployee = employeeRepository.save(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            updatedEmployee.setId(id);
            Employee savedEmployee = employeeRepository.save(updatedEmployee);
            return ResponseEntity.ok(savedEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
