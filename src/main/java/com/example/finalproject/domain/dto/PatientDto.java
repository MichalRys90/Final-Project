package com.example.finalproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class PatientDto {

    private long id;
    private long userId;
    private Set<UserRoleDto> guardians;
    private List<MedicinesDto> medicinesList;
}
