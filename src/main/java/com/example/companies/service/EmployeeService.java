package com.example.companies.service;

import com.example.companies.domain.Employee;
import com.example.companies.exception.NotFoundException;
import com.example.companies.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findOneByEmail(String email) {
        return employeeRepository.findOneByEmail(email)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }

    public void deleteAllWithoutEmployersByEmails(List<String> emails) {
        for (String email : emails) {
            Employee employee = employeeRepository.findOneByEmail(email)
                    .orElseThrow(() -> new NotFoundException("Employee with this email does not exist"));
            if (employee.getEmployers().isEmpty()) {
                employeeRepository.delete(employee);
            }
        }
    }

}
