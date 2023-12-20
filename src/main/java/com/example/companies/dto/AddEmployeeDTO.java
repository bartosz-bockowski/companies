package com.example.companies.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddEmployeeDTO extends EmployeeDTO {

    private boolean existing;

}
