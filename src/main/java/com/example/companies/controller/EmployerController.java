package com.example.companies.controller;

import com.example.companies.command.ExtendedEmployerCommand;
import com.example.companies.domain.Employer;
import com.example.companies.dto.AddEmployerDTO;
import com.example.companies.dto.EmployerDTO;
import com.example.companies.exception.MustBeNullException;
import com.example.companies.service.EmployerService;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@OpenAPIDefinition(info = @Info(title = "Companies API",
        description = "API for creating companies",
        version = "1.0",
        contact = @Contact(
                name = "Bartosz Boćkowski",
                email = "bboc12357@gmail.com",
                url = "https://github.com/bartosz-bockowski"
        ),
        license = @License(
                name = "MIT Licence",
                url = "https://opensource.org/licenses/mit-license.php"
        )
))
@RestController
@RequestMapping("/api/v1/employer")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AddEmployerDTO> save(@Valid @RequestBody ExtendedEmployerCommand employerCommand) {
        if (Objects.nonNull(employerCommand.getId())) {
            throw new MustBeNullException("ID must be null");
        }
        return new ResponseEntity<>(employerService.save(employerCommand), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployerDTO>> listAll(@SortDefault("id") Pageable pageable, @QuerydslPredicate(root = Employer.class) Predicate predicate) {
        return new ResponseEntity<>(employerService.listAll(pageable, predicate).stream()
                .map(employer -> modelMapper.map(employer, EmployerDTO.class))
                .toList(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AddEmployerDTO> update(@Valid @RequestBody ExtendedEmployerCommand extendedEmployerCommand) {
        employerService.throwIfDoesNotExistById(extendedEmployerCommand.getId());
        return new ResponseEntity<>(employerService.update(extendedEmployerCommand), HttpStatus.CREATED);
    }

}
