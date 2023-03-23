package com.csys.template.search;

import com.csys.template.domain.Donation;
import com.csys.template.domain.Patient;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DonationSearch {

    public static Specification<Donation> getSearch(String typeIdentity, String numIdentity){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(typeIdentity!=null && !(typeIdentity.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("typeIdentity"), typeIdentity));
            }
            if(numIdentity!=null && !(numIdentity.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("numIdentity"), numIdentity));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
