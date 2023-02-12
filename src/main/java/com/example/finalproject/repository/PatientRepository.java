package com.example.finalproject.repository;

import com.example.finalproject.domain.Patient;
import com.example.finalproject.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Patient findByUser(User user);
}
