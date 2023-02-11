package com.example.finalproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    private long userId;
    private String username;
    private String userKey;
    private String password;
    private String mail;
    private Set<GuardianDto> guardians;
    private Set<PatientDto> patients;

}
