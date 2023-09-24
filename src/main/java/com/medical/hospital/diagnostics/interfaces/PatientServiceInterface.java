package com.medical.hospital.diagnostics.interfaces;

import com.medical.hospital.diagnostics.medicineresources.dto.PatientDTO;

import java.util.List;

public interface PatientServiceInterface {
    List<PatientDTO> getPatientsList();
    PatientDTO createPatient(PatientDTO patientDTO);
}
