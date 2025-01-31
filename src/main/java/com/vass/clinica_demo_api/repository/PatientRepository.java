package com.vass.clinica_demo_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vass.clinica_demo_api.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}