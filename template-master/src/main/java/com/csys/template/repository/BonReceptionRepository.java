package com.csys.template.repository;

import com.csys.template.domain.BonReception;
import com.csys.template.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonReceptionRepository extends JpaRepository<BonReception, String> {
    List<BonReception> findByCode(String code);

}
