package com.example.finalproject.repository;

import com.example.finalproject.domain.Medicines;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MedicinesRepository extends CrudRepository<Medicines, Long> {
}
