package com.example.finalproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoleDto {

    private long id;
    private long guardianId;
    private long patientId;
}
