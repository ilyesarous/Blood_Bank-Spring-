package com.csys.template.repository;

import com.csys.template.domain.Donation;
import com.csys.template.domain.Patient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
    Donation findByCode(String code);
    List<Donation> findAll(Specification<Donation> donation);
}
