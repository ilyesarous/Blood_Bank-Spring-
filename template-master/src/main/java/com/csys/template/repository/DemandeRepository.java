package com.csys.template.repository;

import com.csys.template.domain.Demande;
import com.csys.template.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,String> {
    Demande findByCode(String code);
    Demande findDemandeByCodeMedecin(String code);
    List<Demande> findByOrderByState();
}
