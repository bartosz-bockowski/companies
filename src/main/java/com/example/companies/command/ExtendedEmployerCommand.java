package com.example.companies.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExtendedEmployerCommand extends EmployerCommand {

    private Long id;

}
