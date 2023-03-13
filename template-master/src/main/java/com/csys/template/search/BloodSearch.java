package com.csys.template.search;

import com.csys.template.domain.Blood;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BloodSearch {
    public static Specification<Blood> getSearch( String group, String rhesus, String given, String receive){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(group!=null && !(group.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("bloodGrp"), group));
            }
            if(rhesus!=null && !(rhesus.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("rhesus"), rhesus));
            }
            if(given!=null && !(given.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("givenTo"), given));
            }
            if(receive!=null && !(receive.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("receivedFrom"), receive));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
