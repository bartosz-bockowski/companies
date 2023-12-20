package com.example.companies.command;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class EmployerCommand {

    @NotEmpty(message = "name cannot be empty")
    protected String name;

    @Length(min = 3, max = 3, message = "length of code must be equal to 3")
    protected String code;

    @NotNull
    @Size(min = 1, message = "you must enter at least one employee")
    protected List<EmployeeCommand> employees;

}
