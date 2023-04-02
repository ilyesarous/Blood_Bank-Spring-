package com.csys.template.repository;

import com.csys.template.domain.Demande;
import com.csys.template.domain.DemandeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeHistoryRepository extends JpaRepository<DemandeHistory,String> {
    DemandeHistory findByCode(String code);
}
