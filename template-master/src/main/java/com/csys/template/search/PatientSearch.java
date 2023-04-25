package com.csys.template.search;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Patient;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PatientSearch {

    public static Specification<Patient> getSearch(String fullNameAr, String phoneNumber, String code){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(fullNameAr!=null && !(fullNameAr.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("fullNameAr"), fullNameAr));
            }
            if(phoneNumber!=null && !(phoneNumber.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("phoneNumber"), phoneNumber));
            }
            if(code!=null && !(code.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("code"), code));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
