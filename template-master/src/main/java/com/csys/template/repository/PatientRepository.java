package com.csys.template.repository;

import com.csys.template.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByCode(String code);
}
