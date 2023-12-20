package com.example.companies.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddEmployerDTO {

    private Long id;

    private String name;

    private String code;

    private List<AddEmployeeDTO> employees;

}
