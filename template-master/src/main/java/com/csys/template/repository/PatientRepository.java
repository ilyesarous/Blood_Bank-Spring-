package com.csys.template.repository;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Patient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByCode(String code);

    List<Patient> findAll(Specification<Patient> patient);

    Patient findByTypeIdentityAndNumIdentity(String phone, String numIdentity);

}
