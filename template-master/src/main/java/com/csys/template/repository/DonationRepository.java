package com.csys.template.repository;

import com.csys.template.domain.Donation;
import com.csys.template.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation,String> {
    Donation findByCode(String code);
}
