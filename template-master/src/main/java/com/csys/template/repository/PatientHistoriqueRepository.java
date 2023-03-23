package com.csys.template.repository;

import com.csys.template.domain.DonationsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PatientHistoriqueRepository extends JpaRepository<DonationsHistory, Integer> {
    DonationsHistory findByCode(String code);
//    DonationsHistory findById (Integer id);
}
