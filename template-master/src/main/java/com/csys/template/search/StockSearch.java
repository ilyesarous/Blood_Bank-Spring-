package com.csys.template.search;

import com.csys.template.domain.Patient;
import com.csys.template.domain.Stock;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StockSearch {
    public static Specification<Stock> getSearch(String blood,String dateperime){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(blood!=null && !(blood.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("blood"), blood));
            }
            if(dateperime!=null && !(dateperime.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("dateperime"), dateperime));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
