package com.example.finalproject.controller;

import com.example.finalproject.domain.Patient;
import com.example.finalproject.domain.UserRole;
import com.example.finalproject.domain.dto.UserRoleDto;
import com.example.finalproject.exception.GuardianWithGivenIdDoesntExist;
import com.example.finalproject.exception.PatientIsAlreadyAdded;
import com.example.finalproject.exception.PatientWithGivenIdDoesntExist;
import com.example.finalproject.mapper.UserRoleMapper;
import com.example.finalproject.service.UserRoleDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/userRoles")
public class UserRoleController {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserRoleDbService userRoleDbService;

    @PostMapping(value = "addPatient/{patientId}/{guardianId}")
    public ResponseEntity<UserRoleDto> addPatientToGuardian(@PathVariable Long patientId, @PathVariable Long guardianId) throws PatientWithGivenIdDoesntExist, GuardianWithGivenIdDoesntExist, PatientIsAlreadyAdded {
        UserRole userRole = userRoleDbService.addPatientToGuardian(patientId, guardianId);
        return ResponseEntity.ok(userRoleMapper.mapToUserRoleDto(userRole));
    }

    @GetMapping(value = "getAllPatients/{guardianId}")
    public ResponseEntity<List<Patient>> allPatients(@PathVariable Long guardianId) throws GuardianWithGivenIdDoesntExist {
        return ResponseEntity.ok(userRoleDbService.showAllPatients(guardianId));
    }
}
