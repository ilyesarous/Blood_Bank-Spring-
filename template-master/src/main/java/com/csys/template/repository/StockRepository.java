package com.csys.template.repository;
import com.csys.template.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findBycode(String code);
}
