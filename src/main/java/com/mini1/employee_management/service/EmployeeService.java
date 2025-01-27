package com.mini1.employee_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini1.employee_management.model.Employee;
import com.mini1.employee_management.respository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(Long id){
		return employeeRepository.findById(id);
	}
	
	public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee){
		return employeeRepository.findById(id)
				.map(existingEmployee -> {
					updatedEmployee.setId(id);
					return employeeRepository.save(updatedEmployee);
				});
	}
	
	public boolean deleteEmployee(Long id) {
		return employeeRepository.findById(id)
				.map(employee -> {
					employeeRepository.delete(employee);
					return true;
				}).orElse(false);
	}
	
}
