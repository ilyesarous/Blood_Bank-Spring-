package com.csys.template.repository;

import com.csys.template.domain.ReceiptAfterDonation;
import com.csys.template.domain.ReceiptBeforeDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptAfterDonationRepository extends JpaRepository<ReceiptAfterDonation,String> {

    ReceiptAfterDonation findByCode(String code);

}
