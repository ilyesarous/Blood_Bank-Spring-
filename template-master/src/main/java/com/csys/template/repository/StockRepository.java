package com.csys.template.repository;
import com.csys.template.domain.Patient;
import com.csys.template.domain.Stock;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findBycode(String code);
//    Stock findBydateperime(String dateperime);
    Stock findByblood(String blood);
//    List<Stock> findAll(Specification<Stock> stock);
}
