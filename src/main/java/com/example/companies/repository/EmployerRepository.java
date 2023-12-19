package com.example.companies.repository;

import com.example.companies.domain.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EmployerRepository extends JpaRepository<Employer, Long>, QuerydslPredicateExecutor<Employer> {
}
