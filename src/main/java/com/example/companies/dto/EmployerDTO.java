package com.example.companies.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployerDTO {

    private Long id;

    private String name;

    private String code;

    private List<EmployeeDTO> employees;

}
