package com.cidenet.infrastructure.entrypoint.api;

import com.cidenet.application.service.EmployeeService;
import com.cidenet.domain.model.Employee;
import com.cidenet.infrastructure.entrypoint.dtos.EmployeeRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeApi {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> registerEmployee(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeService.saveEmployee(employeeRequestDto);
        return ResponseEntity.ok(employee);
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employee = employeeService.getAll();
        return ResponseEntity.ok(employee);
    }

}
