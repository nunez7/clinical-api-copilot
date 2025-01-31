package com.vass.clinica_demo_api.services;

import com.vass.clinica_demo_api.models.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient insert(Patient patient);
    Optional<Patient> update(Long id, Patient patientDetails);
    void delete(Long id);
    Optional<Patient> findById(Long id);
    List<Patient> findAll();
}