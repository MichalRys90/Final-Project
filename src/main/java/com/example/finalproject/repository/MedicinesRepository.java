package com.example.finalproject.repository;

import com.example.finalproject.domain.Medicines;
import com.example.finalproject.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MedicinesRepository extends CrudRepository<Medicines, Long> {
    Medicines findByNameAndPatient(String name, Patient patient);
    List<Medicines> findAllByPatient(Patient patient);
}
