package com.csys.template.repository;

import com.csys.template.domain.Donations_history;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientHistoriqueRepository extends JpaRepository<Donations_history, Integer> {
    Donations_history findByPatientCode(String code);
}
