package com.example.companies.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class EmployeeCommand {

    @NotEmpty(message = "email address cannot be empty")
    private String email;

}
