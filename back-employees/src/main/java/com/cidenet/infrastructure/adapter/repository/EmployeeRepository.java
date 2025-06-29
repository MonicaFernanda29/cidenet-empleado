package com.cidenet.infrastructure.adapter.repository;

import com.cidenet.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT COUNT(*) > 0 FROM employees WHERE document_type = :type AND document_number = :number", nativeQuery = true)
    boolean existsByDocumentTypeAndDocumentNumber(@Param("type") String type, @Param("number") String number);

    @Query(value = "SELECT COUNT(*) > 0 FROM employees WHERE email = :email", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);

}
