package com.csys.template.service;


import com.csys.template.domain.Donation;
import com.csys.template.dto.DonationDTO;
import com.csys.template.factory.DonationFactory;

import com.csys.template.repository.DonationRepository;
import com.google.common.base.Preconditions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;


    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }
    @Transactional(readOnly = true)
    public List<DonationDTO> findAll() {
        List<Donation> Donations = donationRepository.findAll();
        List<DonationDTO> donationDTOS = DonationFactory.DonationsToDonationDTO(Donations);

        return donationDTOS;
    }


    public DonationDTO addDonation(DonationDTO donationDTO) {
        Donation donation = DonationFactory.DonationDTOToDonation(donationDTO);
        donation = donationRepository.save(donation);
        return DonationFactory.DonationToDonationDTO(donation);
    }


    public DonationDTO updateCounter(DonationDTO donationDTO){
        Donation donation = donationRepository.findByCode(donationDTO.getCode());
        Preconditions.checkArgument (donation != null, "Donation has been deleted");
        Donation donation1 = donationRepository.save(DonationFactory.DonationDTOToDonation(donationDTO));
        return DonationFactory.DonationToDonationDTO(donation1);
    }
}
