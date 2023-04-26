package com.csys.template.repository;

import com.csys.template.domain.Blood;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@EnableJpaRepositories
@Repository
public interface BloodRepository extends JpaRepository<Blood, String> {

    List<Blood> findBybloodGrp(String grp);

    Blood findBybloodGrpAndRhesus(String grp,String rhesus);
    Blood findByCodeBlood(Integer codeBlood);

    List<Blood> findAll(Specification<Blood> specification);
}
