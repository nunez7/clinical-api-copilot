package com.vass.clinica_demo_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vass.clinica_demo_api.models.Patient;
import com.vass.clinica_demo_api.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public Patient insert(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public Optional<Patient> update(Long id, Patient patientDetails) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            Patient existingPatient = patient.get();
            existingPatient.setFirstName(patientDetails.getFirstName());
            existingPatient.setLastName(patientDetails.getLastName());
            existingPatient.setAge(patientDetails.getAge());
            return Optional.of(patientRepository.save(existingPatient));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
