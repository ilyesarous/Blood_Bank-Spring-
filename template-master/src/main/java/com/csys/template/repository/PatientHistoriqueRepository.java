package com.csys.template.repository;

import com.csys.template.domain.PatientHistorique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientHistoriqueRepository extends JpaRepository<PatientHistorique, Integer> {
    PatientHistorique findByPatientCode(String code);
}
