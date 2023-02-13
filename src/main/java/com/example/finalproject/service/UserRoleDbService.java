package com.example.finalproject.service;


import com.example.finalproject.domain.Guardian;
import com.example.finalproject.domain.Patient;
import com.example.finalproject.domain.UserRole;
import com.example.finalproject.exception.GuardianWithGivenIdDoesntExist;
import com.example.finalproject.exception.PatientIsAlreadyAdded;
import com.example.finalproject.exception.PatientWithGivenIdDoesntExist;
import com.example.finalproject.repository.GuardianRepository;
import com.example.finalproject.repository.PatientRepository;
import com.example.finalproject.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleDbService {

    private final UserRoleRepository userRoleRepository;
    private final PatientRepository patientRepository;
    private final GuardianRepository guardianRepository;

    public UserRole addPatientToGuardian(long patientId, long guardianId) throws PatientWithGivenIdDoesntExist, GuardianWithGivenIdDoesntExist, PatientIsAlreadyAdded {
        Patient patient = patientRepository.findById(patientId).orElseThrow(PatientWithGivenIdDoesntExist::new);
        Guardian guardian = guardianRepository.findById(guardianId).orElseThrow(GuardianWithGivenIdDoesntExist::new);

        UserRole userRole = userRoleRepository.findByPatientAndGuardian(patient, guardian);

        if (userRole == null) {
            return userRoleRepository.save(new UserRole(guardian, patient));
        }
        throw new PatientIsAlreadyAdded();
    }

    public List<Patient> showAllPatients(long guardianId) throws GuardianWithGivenIdDoesntExist {
        Guardian guardian = guardianRepository.findById(guardianId).orElseThrow(GuardianWithGivenIdDoesntExist::new);
        List<UserRole> userRoles = userRoleRepository.findAllByGuardian(guardian);
        List<Patient> patients = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            patients.add(userRole.getPatient());
        }
        return patients;
    }
}
