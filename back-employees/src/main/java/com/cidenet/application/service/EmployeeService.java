package com.cidenet.application.service;


import com.cidenet.domain.enums.Country;
import com.cidenet.domain.model.Employee;
import com.cidenet.infrastructure.adapter.repository.EmployeeRepository;
import com.cidenet.infrastructure.entrypoint.dtos.EmployeeRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return  employeeRepository.findAll();
    }

    @Transactional
    public Employee saveEmployee(EmployeeRequestDto employeeRequestDto) {

        // Validación de unicidad de identificación
        if (employeeRepository.existsByDocumentTypeAndDocumentNumber(
                employeeRequestDto.getDocumentType().name(), employeeRequestDto.getDocumentNumber())) {
            throw new IllegalArgumentException("Ya existe un empleado con este tipo y número de identificación.");
        }

        // Validar rango de fecha de ingreso
        LocalDate hoy = LocalDate.now();
        if (employeeRequestDto.getEntryDate().isAfter(hoy) ||
                employeeRequestDto.getEntryDate().isBefore(hoy.minusMonths(1))) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser mayor a hoy ni menor a hace un mes.");
        }

        // Generar correo electrónico único
        String email = this.generateEmail(employeeRequestDto.getFirstName(), employeeRequestDto.getFirstSurname(), employeeRequestDto.getCountry());

        // Crear y guardar entidad
        Employee empleado = Employee.builder()
                .firstSurname(employeeRequestDto.getFirstSurname())
                .secondLastName(employeeRequestDto.getSecondLastName())
                .firstName(employeeRequestDto.getFirstName())
                .lastName(employeeRequestDto.getLastName())
                .country(employeeRequestDto.getCountry())
                .documentType(employeeRequestDto.getDocumentType())
                .documentNumber(employeeRequestDto.getDocumentNumber())
                .email(email)
                .entryDate(employeeRequestDto.getEntryDate())
                .workArea(employeeRequestDto.getWorkArea())
                .state(true)
                .createDate(LocalDateTime.now())
                .build();

        return employeeRepository.save(empleado);
    }

    private String generateEmail(String firstName, String firstSurname, Country country) {
        String nameBase = normalizar(firstName);
        String firstSurnameBase = normalizar(firstSurname);
        String emailBase = (nameBase + "." + firstSurnameBase).toLowerCase(Locale.ROOT);
        String domain = country.getDomain();

        String email = emailBase + "@" + domain;
        int count = 1;

        // Si ya existe, se le añade un número
        while (employeeRepository.existsByEmail(email)) {
            email = emailBase + "." + count + "@" + domain;
            count++;
        }

        return email;
    }

    private String normalizar(String text) {
        return text
                .replaceAll("\\s+", "")        // Quita espacios
                .replaceAll("[^A-Z]", "")      // Quita cualquier cosa que no sea A-Z
                .toLowerCase(Locale.ROOT);     // Convierte a minúsculas
    }
}
