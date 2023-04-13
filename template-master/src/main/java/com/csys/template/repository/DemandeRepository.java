package com.csys.template.repository;

import com.csys.template.domain.Demande;
import com.csys.template.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,String> {
    Demande findByCode(String code);
    Demande findDemandeByCodeMedecin(String code);
}
