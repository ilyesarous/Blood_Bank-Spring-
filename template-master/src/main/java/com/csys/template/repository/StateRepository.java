package com.csys.template.repository;


import com.csys.template.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State,String> {
    State findByName(String name);
}
