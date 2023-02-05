package com.example.finalproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "GUARDIANS")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "USER_ROLE_ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GUARDIAN_ID")
    private Guardian guardian;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;
}
