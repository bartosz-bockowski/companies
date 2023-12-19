package com.example.companies.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class EmployeeCommand {

    @NotEmpty
    private String email;

}
