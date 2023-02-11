package com.example.finalproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MedicinesDto {

    private long id;
    private BigDecimal price;
    private String name;
    private int quantity;
    private long patientId;
}
