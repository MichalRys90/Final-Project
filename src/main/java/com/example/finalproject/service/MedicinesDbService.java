package com.example.finalproject.service;

import com.example.finalproject.domain.Medicines;
import com.example.finalproject.domain.Patient;
import com.example.finalproject.exception.PatientWithGivenIdDoesntExist;
import com.example.finalproject.repository.MedicinesRepository;
import com.example.finalproject.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicinesDbService {

    private final MedicinesRepository medicinesRepository;
    private final PatientRepository patientRepository;

    public Medicines saveMedicine(Medicines medicines) {
        Medicines medicines1 = medicinesRepository.findByNameAndPatient(medicines.getName(), medicines.getPatient());
        if (medicines1 == null) {
            return medicinesRepository.save(medicines);
        } else {
            medicines1.setQuantity(medicines1.getQuantity() + medicines.getQuantity());
        }
        return medicinesRepository.save(medicines1);
    }

    public List<Medicines> findAllMedicines(Long patientId) throws PatientWithGivenIdDoesntExist {
        Patient patient = patientRepository.findById(patientId).orElseThrow(PatientWithGivenIdDoesntExist::new);
        return medicinesRepository.findAllByPatient(patient);
    }

    public void removeMedicine(Long patientId, String name) throws PatientWithGivenIdDoesntExist {
        Patient patient = patientRepository.findById(patientId).orElseThrow(PatientWithGivenIdDoesntExist::new);
        Medicines medicines = medicinesRepository.findByNameAndPatient(name, patient);
        medicinesRepository.delete(medicines);
    }
}
