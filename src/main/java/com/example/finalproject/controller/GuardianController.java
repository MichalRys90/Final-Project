package com.example.finalproject.controller;

import com.example.finalproject.domain.Guardian;
import com.example.finalproject.domain.Patient;
import com.example.finalproject.domain.dto.GuardianDto;
import com.example.finalproject.domain.dto.PatientDto;
import com.example.finalproject.exception.GuardianExist;
import com.example.finalproject.exception.PatientExist;
import com.example.finalproject.exception.UserWithGivenIdDoesntExist;
import com.example.finalproject.mapper.GuardianMapper;
import com.example.finalproject.service.GuardianDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/guardians")
public class GuardianController {

    @Autowired
    private GuardianDbService guardianDbService;
    @Autowired
    private GuardianMapper guardianMapper;

    @PostMapping(value = "addGuardian")
    public ResponseEntity<GuardianDto> addGuardian(@RequestBody GuardianDto guardianDto) throws UserWithGivenIdDoesntExist, GuardianExist {
        Guardian guardian = guardianMapper.mapToGuardian(guardianDto);
        guardianDbService.saveGuardian(guardian);
        return ResponseEntity.ok(guardianMapper.mapToGuardianDto(guardian));
    }
}
