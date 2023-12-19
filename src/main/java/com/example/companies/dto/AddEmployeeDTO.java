package com.example.companies.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddEmployeeDTO extends EmployeeDTO {

    private boolean existing;
    
}
