package com.example.finalproject.service;

import com.example.finalproject.domain.Patient;
import com.example.finalproject.exception.PatientExist;
import com.example.finalproject.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientDbService {

    private final PatientRepository patientRepository;

    public Patient savePatient(Patient patient) throws PatientExist {
        Patient patient1 = patientRepository.findByUser(patient.getUser());

        if (patient1 == null) {
            return patientRepository.save(patient);
        }
        throw new PatientExist();
    }
}
