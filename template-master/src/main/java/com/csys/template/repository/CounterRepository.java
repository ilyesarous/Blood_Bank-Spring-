package com.csys.template.repository;

import com.csys.template.domain.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, String> {
    Counter findByType(String type);
}
