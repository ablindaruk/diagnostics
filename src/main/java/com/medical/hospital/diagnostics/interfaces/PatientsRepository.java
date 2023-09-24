package com.medical.hospital.diagnostics.interfaces;

import com.medical.hospital.diagnostics.medicineresources.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Integer> {
}
