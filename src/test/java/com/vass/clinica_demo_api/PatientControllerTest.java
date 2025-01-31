package com.vass.clinica_demo_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.vass.clinica_demo_api.controller.PatientController;
import com.vass.clinica_demo_api.models.Patient;
import com.vass.clinica_demo_api.services.PatientService;

public class PatientControllerTest {
     @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPatients() {
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();
        Iterable<Patient> patients = Arrays.asList(patient1, patient2);

        when(patientService.findAll()).thenReturn((List<Patient>) patients);

        ResponseEntity<Iterable<Patient>> response = patientController.getPatients();

        assertEquals(ResponseEntity.ok(patients), response);
        verify(patientService, times(1)).findAll();
    }

    @Test
    public void testGetPatientById() {
        Long patientId = 1L;
        Patient patient = new Patient();
        Optional<Patient> optionalPatient = Optional.of(patient);

        when(patientService.findById(patientId)).thenReturn(optionalPatient);

        ResponseEntity<Patient> response = patientController.getPatientById(patientId);

        assertEquals(ResponseEntity.ok(patient), response);
        verify(patientService, times(1)).findById(patientId);
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();

        when(patientService.insert(patient)).thenReturn(patient);

        Patient response = patientController.createPatient(patient);

        assertEquals(patient, response);
        verify(patientService, times(1)).insert(patient);
    }

    @Test
    public void testUpdatePatient() {
        Long patientId = 1L;
        Patient patientDetails = new Patient();
        Patient updatedPatient = new Patient();
        Optional<Patient> optionalUpdatedPatient = Optional.of(updatedPatient);

        when(patientService.update(patientId, patientDetails)).thenReturn(optionalUpdatedPatient);

        ResponseEntity<Patient> response = patientController.updatePatient(patientId, patientDetails);

        assertEquals(ResponseEntity.ok(updatedPatient), response);
        verify(patientService, times(1)).update(patientId, patientDetails);
    }

    @Test
    public void testDeletePatient() {
        Long patientId = 1L;

        doNothing().when(patientService).delete(patientId);

        ResponseEntity<Void> response = patientController.deletePatient(patientId);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(patientService, times(1)).delete(patientId);
    }
}
