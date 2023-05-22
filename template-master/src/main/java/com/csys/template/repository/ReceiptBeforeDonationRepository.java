package com.csys.template.repository;

import com.csys.template.domain.Patient;
import com.csys.template.domain.ReceiptBeforeDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptBeforeDonationRepository extends JpaRepository<ReceiptBeforeDonation,String> {

    ReceiptBeforeDonation findTopByOrderByIdDesc();
}
