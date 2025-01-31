package com.vass.clinica_demo_api.services;

import com.vass.clinica_demo_api.models.ClinicalData;
import com.vass.clinica_demo_api.repository.ClinicalDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicalDataServiceImpl implements ClinicalDataService {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;

    @Override
    @Transactional
    public ClinicalData save(ClinicalData clinicalData) {
        return clinicalDataRepository.save(clinicalData);
    }

    @Override
    @Transactional
    public Optional<ClinicalData> update(Long id, ClinicalData clinicalDataDetails) {
        Optional<ClinicalData> clinicalData = clinicalDataRepository.findById(id);
        if (clinicalData.isPresent()) {
            ClinicalData existingClinicalData = clinicalData.get();
            existingClinicalData.setComponentName(clinicalDataDetails.getComponentName());
            existingClinicalData.setComponentValue(clinicalDataDetails.getComponentValue());
            existingClinicalData.setId(clinicalDataDetails.getId());
            return Optional.of(clinicalDataRepository.save(existingClinicalData));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clinicalDataRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClinicalData> findById(Long id) {
        return clinicalDataRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClinicalData> findByPatientId(Long patientId) {
        return clinicalDataRepository.findByPatientId(patientId);
    }
}