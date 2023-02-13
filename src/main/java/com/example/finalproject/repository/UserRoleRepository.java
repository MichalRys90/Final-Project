package com.example.finalproject.repository;

import com.example.finalproject.domain.Guardian;
import com.example.finalproject.domain.Patient;
import com.example.finalproject.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    UserRole findByPatientAndGuardian(Patient patient, Guardian guardian);

    List<UserRole> findAllByGuardian(Guardian guardian);
}
