package com.vass.clinica_demo_api.repository;

import com.vass.clinica_demo_api.models.ClinicalData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
    List<ClinicalData> findByPatientId(Long patientId);
}