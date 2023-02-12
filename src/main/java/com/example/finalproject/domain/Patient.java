package com.example.finalproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PATIENTS")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "PATIENT_ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name="PATIENTS")
    @OneToMany(targetEntity = UserRole.class,
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<UserRole> guardians = new HashSet<>();

    @Column(name="MEDICINES")
    @OneToMany(targetEntity = Medicines.class,
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Medicines> medicinesList = new ArrayList<>();

    public Patient(User user, Set<UserRole> guardians, List<Medicines> medicinesList) {
        this.user = user;
        this.guardians = guardians;
        this.medicinesList = medicinesList;
    }
}
