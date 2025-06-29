package com.cidenet.infrastructure.entrypoint.dtos;

import com.cidenet.domain.enums.Country;
import com.cidenet.domain.enums.DocumentType;
import com.cidenet.domain.enums.WorkArea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeRequestDto {
    @NotBlank
    @Pattern(regexp = "^[A-Z]{1,20}$", message = "Solo letras mayúsculas sin acentos ni Ñ, máximo 20 letras.")
    private String firstSurname;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{1,20}$", message = "Solo letras mayúsculas sin acentos ni Ñ, máximo 20 letras.")
    private String secondLastName;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{1,20}$", message = "Solo letras mayúsculas sin acentos ni Ñ, máximo 20 letras.")
    private String firstName;

    @Pattern(regexp = "^[A-Z ]{0,50}$", message = "Solo letras mayúsculas sin acentos ni Ñ, máximo 50 letras y espacios.")
    private String lastName;

    @NotNull
    private Country country;

    @NotNull
    private DocumentType documentType;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9\\-]{1,20}$", message = "Solo letras, números y guiones. Máximo 20 caracteres.")
    private String documentNumber;

    @NotNull
    @PastOrPresent(message = "La fecha no puede ser en el futuro.")
    private LocalDate entryDate;

    @NotNull
    private WorkArea workArea;
}
