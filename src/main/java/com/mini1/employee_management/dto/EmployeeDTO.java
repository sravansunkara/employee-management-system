package com.mini1.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	
    private Long id;
    private String name;
    private String email;
    private String position;

}
