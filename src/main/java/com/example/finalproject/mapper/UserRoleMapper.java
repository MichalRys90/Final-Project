package com.example.finalproject.mapper;

import com.example.finalproject.domain.UserRole;
import com.example.finalproject.domain.dto.UserRoleDto;
import com.example.finalproject.repository.GuardianRepository;
import com.example.finalproject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRoleMapper {

    @Autowired
    private GuardianRepository guardianRepository;
    @Autowired
    private PatientRepository patientRepository;

    public UserRoleDto mapToUserRoleDto(final UserRole userRole) {
        return new UserRoleDto(userRole.getId(),
                userRole.getGuardian().getId(),
                userRole.getPatient().getId());
    }

    public UserRole mapToUserRole(final UserRoleDto userRoleDto) throws Exception {
        return new UserRole(userRoleDto.getId(),
                guardianRepository.findById(userRoleDto.getGuardianId()).orElseThrow(Exception::new),
                patientRepository.findById(userRoleDto.getPatientId()).orElseThrow(Exception::new));
    }

    public Set<UserRoleDto> mapToUserRoleDtoSet(final Set<UserRole> userRoles) {
        return userRoles.stream()
                .map(this::mapToUserRoleDto)
                .collect(Collectors.toSet());
    }
}
