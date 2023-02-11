package com.example.finalproject.mapper;

import com.example.finalproject.domain.Patient;
import com.example.finalproject.domain.dto.PatientDto;
import com.example.finalproject.repository.PatientRepository;
import com.example.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientMapper {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private MedicinesMapper medicinesMapper;

    public PatientDto mapToPatientDto(final Patient patient) {
        return new PatientDto(patient.getId(),
                patient.getUser().getUserId(),
                userRoleMapper.mapToUserRoleDtoSet(patient.getGuardians()),
                medicinesMapper.mapToMedicinesDtoList(patient.getMedicinesList()));
    }

    public Patient mapToPatient(final PatientDto patientDto) throws Exception {
        return new Patient(patientDto.getId(),
                userRepository.findById(patientDto.getUserId()).orElseThrow(Exception::new),
                new HashSet<>(),
                new ArrayList<>());
    }

    public Set<PatientDto> mapToPatientDtoSet(final Set<Patient> patients) {
        return patients.stream()
                .map(this::mapToPatientDto)
                .collect(Collectors.toSet());
    }
}
