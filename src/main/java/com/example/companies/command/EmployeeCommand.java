package com.example.companies.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeCommand {

    @NotEmpty(message = "email address cannot be empty")
    private String email;

}
