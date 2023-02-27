package com.csys.template.repository;

import com.csys.template.domain.DonationsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientHistoriqueRepository extends JpaRepository<DonationsHistory, Integer> {
    DonationsHistory findByPatientCode(String code);
}
