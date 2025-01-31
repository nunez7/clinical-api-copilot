package com.vass.clinica_demo_api;

import com.vass.clinica_demo_api.models.ClinicalData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.vass.clinica_demo_api.controller.ClinicalDataController;
import com.vass.clinica_demo_api.services.ClinicalDataService;

public class ClinicalDataControllerTest {
    
    @Mock
    private ClinicalDataService clinicalDataService;

    @InjectMocks
    private ClinicalDataController clinicalDataController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetClinicalDataById() {
        ClinicalData clinicalData = new ClinicalData();
        when(clinicalDataService.findById(anyLong())).thenReturn(Optional.of(clinicalData));

        ResponseEntity<ClinicalData> response = clinicalDataController.getClinicalDataById(1L);
        assertEquals(ResponseEntity.ok(clinicalData), response);
    }

    @Test
    public void testGetClinicalDataById_NotFound() {
        when(clinicalDataService.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<ClinicalData> response = clinicalDataController.getClinicalDataById(1L);
        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testUpdateClinicalData() {
        ClinicalData clinicalData = new ClinicalData();
        when(clinicalDataService.update(anyLong(), any(ClinicalData.class))).thenReturn(Optional.of(clinicalData));

        ResponseEntity<ClinicalData> response = clinicalDataController.updateClinicalData(1L, clinicalData);
        assertEquals(ResponseEntity.ok(clinicalData), response);
    }

    @Test
    public void testUpdateClinicalData_NotFound() {
        when(clinicalDataService.update(anyLong(), any(ClinicalData.class))).thenReturn(Optional.empty());

        ResponseEntity<ClinicalData> response = clinicalDataController.updateClinicalData(1L, new ClinicalData());
        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testDeleteClinicalData() {
        ResponseEntity<Void> response = clinicalDataController.deleteClinicalData(1L);
        assertEquals(ResponseEntity.noContent().build(), response);
    }
}
