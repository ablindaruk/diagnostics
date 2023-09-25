package com.medical.hospital.diagnostics.medicineresources.services;

import com.medical.hospital.diagnostics.exceptions.PatientNotFoundException;
import com.medical.hospital.diagnostics.interfaces.PatientServiceInterface;
import com.medical.hospital.diagnostics.interfaces.PatientsRepository;
import com.medical.hospital.diagnostics.medicineresources.dto.PatientDTO;
import com.medical.hospital.diagnostics.medicineresources.entity.Patient;
import com.medical.hospital.diagnostics.medicineresources.mappers.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientService implements PatientServiceInterface {
    @Autowired private PatientsRepository patientsRepo;
    private final PatientMapper patientMapper;

    @Override
    public List<PatientDTO> getPatientsList() {
        return patientMapper.patientListToDTOlist(patientsRepo.findAll());
    }
    @Override
    public PatientDTO getPatientItemDetails(int id) {
        try {
            return patientMapper.patientToPatientDTO(patientsRepo.getById(id));
        } catch (Exception e) {
            throw new PatientNotFoundException("Invalid patient item id");
        }
    }
    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient newPatient = patientMapper.dtoToPatient(patientDTO);
        Patient savedPatient = patientsRepo.save(newPatient);
        return patientMapper.patientToPatientDTO(savedPatient);
    }
}
