package com.example.finalproject.controller;

import com.example.finalproject.domain.Patient;
import com.example.finalproject.domain.dto.PatientDto;
import com.example.finalproject.exception.PatientExist;
import com.example.finalproject.exception.UserWithGivenIdDoesntExist;
import com.example.finalproject.mapper.PatientMapper;
import com.example.finalproject.service.PatientDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/patients")
public class PatientController {

    @Autowired
    private PatientDbService patientDbService;
    @Autowired
    private PatientMapper patientMapper;

    @PostMapping(value = "addPatient")
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto) throws UserWithGivenIdDoesntExist, PatientExist {
        Patient patient = patientMapper.mapToPatient(patientDto);
        patientDbService.savePatient(patient);
        return ResponseEntity.ok(patientMapper.mapToPatientDto(patient));
    }
}
