package com.vass.clinica_demo_api.services;

import com.vass.clinica_demo_api.models.ClinicalData;

import java.util.List;
import java.util.Optional;

public interface ClinicalDataService {
    ClinicalData save(ClinicalData clinicalData);
    Optional<ClinicalData> update(Long id, ClinicalData clinicalDataDetails);
    void delete(Long id);
    Optional<ClinicalData> findById(Long id);
    List<ClinicalData> findByPatientId(Long patientId);
}