package com.csys.template.repository;

import com.csys.template.domain.Blood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRepository extends JpaRepository<Blood, String>{
    Blood findBybloodType(String Type);
    Blood findByCodeBlood(Integer codeBlood);




}
