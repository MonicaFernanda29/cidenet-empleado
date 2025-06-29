package com.cidenet;

import com.cidenet.application.service.EmployeeService;
import com.cidenet.domain.enums.Country;
import com.cidenet.domain.enums.DocumentType;
import com.cidenet.domain.enums.WorkArea;
import com.cidenet.domain.model.Employee;
import com.cidenet.infrastructure.adapter.repository.EmployeeRepository;
import com.cidenet.infrastructure.entrypoint.dtos.EmployeeRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldThrowWhenDocumentExists() {
        EmployeeRequestDto dto = buildValidDto();
        when(employeeRepository.existsByDocumentTypeAndDocumentNumber("CC", "123456")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> employeeService.saveEmployee(dto));
    }

    @Test
    void shouldGenerateEmailSuccessfully() {
        EmployeeRequestDto dto = buildValidDto();
        when(employeeRepository.existsByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(false);
        when(employeeRepository.existsByEmail(any())).thenReturn(false);
        when(employeeRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Employee saved = employeeService.saveEmployee(dto);
        assertEquals("juan.perez@cidenet.com.co", saved.getEmail());
    }

    private EmployeeRequestDto buildValidDto() {
        EmployeeRequestDto dto = new EmployeeRequestDto();
        dto.setFirstName("JUAN");
        dto.setFirstSurname("PEREZ");
        dto.setSecondLastName("GOMEZ");
        dto.setLastName("CARLOS");
        dto.setCountry(Country.COLOMBIA);
        dto.setDocumentType(DocumentType.CC);
        dto.setDocumentNumber("123456");
        dto.setEntryDate(LocalDate.now().minusDays(3));
        dto.setWorkArea(WorkArea.OPERACION);
        return dto;
    }
}
