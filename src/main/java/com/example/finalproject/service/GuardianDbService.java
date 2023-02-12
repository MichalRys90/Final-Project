package com.example.finalproject.service;

import com.example.finalproject.domain.Guardian;
import com.example.finalproject.domain.Patient;
import com.example.finalproject.exception.GuardianExist;
import com.example.finalproject.exception.PatientExist;
import com.example.finalproject.repository.GuardianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuardianDbService {

    private final GuardianRepository guardianRepository;

    public Guardian saveGuardian(Guardian guardian) throws GuardianExist {
        Guardian guardian1 = guardianRepository.findByUser(guardian.getUser());

        if (guardian1 == null) {
            return guardianRepository.save(guardian);
        }
        throw new GuardianExist();
    }
}
