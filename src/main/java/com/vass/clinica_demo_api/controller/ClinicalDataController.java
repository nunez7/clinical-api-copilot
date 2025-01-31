package com.vass.clinica_demo_api.controller;

import com.vass.clinica_demo_api.dto.ClinicalDataRequest;
import com.vass.clinica_demo_api.models.ClinicalData;
import com.vass.clinica_demo_api.models.Patient;
import com.vass.clinica_demo_api.services.ClinicalDataService;
import com.vass.clinica_demo_api.services.PatientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/clinicaldata")
public class ClinicalDataController {

    private final ClinicalDataService clinicalDataService;

    private final PatientService patientService;

    private static final Logger logger = LoggerFactory.getLogger(ClinicalDataController.class);


    public ClinicalDataController(ClinicalDataService clinicalDataService, PatientService patientService) {
        this.clinicalDataService = clinicalDataService;
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalData> getClinicalDataById(@PathVariable Long id) {
        logger.debug("Fetching clinical data with id {}", id);
        
        Optional<ClinicalData> clinicalData = clinicalDataService.findById(id);
        if (clinicalData.isPresent()) {
            return ResponseEntity.ok(clinicalData.get());
        } else {
            logger.error("Clinical data not found with id {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ClinicalData createClinicalData(@RequestBody ClinicalDataRequest clinicalData) {
        logger.debug("Creating new clinical data");
        
        ClinicalData clinicalDataModel = new ClinicalData();
        clinicalDataModel.setComponentName(clinicalData.getComponentName());
        clinicalDataModel.setComponentValue(clinicalData.getComponentValue());

        Optional<Patient> optionalPatient = patientService.findById(clinicalData.getPatientId());
        if (!optionalPatient.isPresent()) {
            throw new RuntimeException("Patient not found");
        }
        Patient newPatient = optionalPatient.get();

        clinicalDataModel.setPatient(newPatient);
        return clinicalDataService.save(clinicalDataModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalData> updateClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalDataDetails) {
        logger.debug("Updating clinical data with id {}", id);
        
        Optional<ClinicalData> updatedClinicalData = clinicalDataService.update(id, clinicalDataDetails);
        if (updatedClinicalData.isPresent()) {
            return ResponseEntity.ok(updatedClinicalData.get());
        } else {
            logger.error("Clinical data not found with id {}", id);
            
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalData(@PathVariable Long id) {
        clinicalDataService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Iterable<ClinicalData>> getClinicalDataByPatientId(@PathVariable Long patientId) {
        logger.debug("Deleting clinical data with id {}", patientId);
        
        Iterable<ClinicalData> clinicalData = clinicalDataService.findByPatientId(patientId);
        if (clinicalData.iterator().hasNext()) {
            return ResponseEntity.ok(clinicalData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}