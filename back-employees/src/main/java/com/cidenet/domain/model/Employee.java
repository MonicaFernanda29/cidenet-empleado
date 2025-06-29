package com.cidenet.domain.model;

import com.cidenet.domain.enums.Country;
import com.cidenet.domain.enums.DocumentType;
import com.cidenet.domain.enums.WorkArea;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"documentType", "documentNumber"}),
                @UniqueConstraint(columnNames = "email")
        }
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String firstSurname;
    private String secondLastName;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String documentNumber;
    private String email;
    private LocalDate entryDate;
    private Boolean state;
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private WorkArea workArea;
}
