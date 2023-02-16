package com.csys.template.repository;

import com.csys.template.domain.donations_history;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientHistoriqueRepository extends JpaRepository<donations_history, Integer> {
    donations_history findByPatientCode(String code);
}
