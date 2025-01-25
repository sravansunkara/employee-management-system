package com.mini1.employee_management.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini1.employee_management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
