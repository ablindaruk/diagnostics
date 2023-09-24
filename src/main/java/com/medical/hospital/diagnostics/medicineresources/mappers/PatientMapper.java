package com.medical.hospital.diagnostics.medicineresources.mappers;

import com.medical.hospital.diagnostics.medicineresources.dto.PatientDTO;
import com.medical.hospital.diagnostics.medicineresources.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);
    PatientDTO patientToPatientDTO(Patient patient);
    Patient dtoToPatient(PatientDTO patientDTO);
    List<PatientDTO> patientListToDTOlist(List<Patient> patientList);
}
