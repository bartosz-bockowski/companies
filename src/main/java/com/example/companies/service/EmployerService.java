package com.example.companies.service;


import com.example.companies.command.ExtendedEmployerCommand;
import com.example.companies.domain.Employee;
import com.example.companies.domain.Employer;
import com.example.companies.dto.AddEmployeeDTO;
import com.example.companies.dto.AddEmployerDTO;
import com.example.companies.dto.EmployeeDTO;
import com.example.companies.exception.NotFoundException;
import com.example.companies.repository.EmployerRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final EmployerRepository employerRepository;

    private final EmployeeService employeeService;

    private final ModelMapper modelMapper;

    public void throwIfDoesNotExistById(Long id) {
        employerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employer with this ID does not exists"));
    }

    public Page<Employer> listAll(Pageable pageable, Predicate predicate) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(predicate);
        return employerRepository.findAll(builder, pageable);
    }

    public AddEmployerDTO save(ExtendedEmployerCommand employerCommand) {
        Employer employer = modelMapper.map(employerCommand, Employer.class);

        employer.setEmployees(employerCommand.getEmployees().stream()
                .map(employeeCommand -> {
                    Employee employee = modelMapper.map(employeeCommand, Employee.class);
                    try {
                        return employeeService.findOneByEmail(employee.getEmail());
                    } catch (NotFoundException e) {
                        return employeeService.save(employee);
                    }
                })
                .filter(Objects::nonNull)
                .toList());

        AddEmployerDTO result = modelMapper.map(employerRepository.save(employer), AddEmployerDTO.class);
        result.setEmployees(employer.getEmployees().stream()
                .map(employee -> {
                    AddEmployeeDTO employeeDTO = modelMapper.map(employee, AddEmployeeDTO.class);
                    employeeDTO.setExisting(!employee.getEmployers().isEmpty());
                    return employeeDTO;
                })
                .toList());

        return result;
    }

    public AddEmployerDTO update(ExtendedEmployerCommand extendedEmployerCommand) {
        Employer oldEmployer = employerRepository.findById(extendedEmployerCommand.getId())
                .orElseThrow(() -> new NotFoundException("Employer with this ID does not exist"));
        List<String> oldEmails = oldEmployer.getEmployees().stream()
                .map(Employee::getEmail)
                .toList();

        AddEmployerDTO result = save(extendedEmployerCommand);

        List<String> newEmails = result.getEmployees().stream()
                .map(EmployeeDTO::getEmail)
                .toList();

        employeeService.deleteAllWithoutEmployersByEmails(oldEmails.stream()
                .filter(oldEmail -> !newEmails.contains(oldEmail))
                .toList());

        return result;
    }
}
