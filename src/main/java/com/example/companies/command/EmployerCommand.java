package com.example.companies.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class EmployerCommand {

    @NotEmpty
    protected String name;

    @Length(min = 3, max = 3)
    protected String code;

    @Size(min = 1)
    protected List<EmployeeCommand> employees;

}
