package com.example.finalproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class GuardianDto {

    private long id;
    private long userId;
    private Set<UserRoleDto> guardians;
}
