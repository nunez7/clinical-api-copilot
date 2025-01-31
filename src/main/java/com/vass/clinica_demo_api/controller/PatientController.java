package com.vass.clinica_demo_api.controller;

import com.vass.clinica_demo_api.handling.ResourceNotFoundException;
import com.vass.clinica_demo_api.models.Patient;
import com.vass.clinica_demo_api.services.PatientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

   private final PatientService patientService;
   private static final Logger logger = LoggerFactory.getLogger(PatientController.class);


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Patient>> getPatients() {
        logger.debug("Fetching all patients");
        Iterable<Patient> patients = patientService.findAll();
        if (patients.iterator().hasNext()) {
            return ResponseEntity.ok(patients);
        } else {
            throw new ResourceNotFoundException("No patients found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        logger.debug("Fetching patient with id {}", id);
        Optional<Patient> patient = patientService.findById(id);
        if (patient.isPresent()) {
            return ResponseEntity.ok(patient.get());
        } else {
            throw new ResourceNotFoundException("Patient not found with id " + id);
        }
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        logger.debug("Creating new patient");
        return patientService.insert(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        logger.debug("Updating patient with id {}", id);
        
        Optional<Patient> updatedPatient = patientService.update(id, patientDetails);
        if (updatedPatient.isPresent()) {
            return ResponseEntity.ok(updatedPatient.get());
        } else {
            throw new ResourceNotFoundException("Patient not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        logger.debug("Deleting patient with id {}", id);
        
        Optional<Patient> patient = patientService.findById(id);
        if (patient.isPresent()) {
            patientService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Patient not found with id " + id);
        }
    }
}