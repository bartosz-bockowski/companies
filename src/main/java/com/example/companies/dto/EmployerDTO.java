package com.example.companies.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployerDTO {

    private Long id;

    private String name;

    private String code;

    private List<EmployeeDTO> employees;

}
