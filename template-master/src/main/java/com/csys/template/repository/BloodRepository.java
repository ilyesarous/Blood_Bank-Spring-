package com.csys.template.repository;

import com.csys.template.domain.Blood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRepository extends JpaRepository<Blood, String> {
    Blood findBybloodType(String bloodCode);
    Blood findByCodeBlood(Integer codeBlood);
}
