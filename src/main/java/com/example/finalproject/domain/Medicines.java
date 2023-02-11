package com.example.finalproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "MEDICINES")
public class Medicines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "MEDICINES_ID", unique = true)
    private long id;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column
    private String name;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

}
