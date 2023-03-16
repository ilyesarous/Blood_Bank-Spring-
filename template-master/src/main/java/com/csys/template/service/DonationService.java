package com.csys.template.service;


import com.csys.template.domain.Donation;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.DonationDTO;
import com.csys.template.factory.DonationFactory;

import com.csys.template.repository.DonationRepository;

import com.csys.template.util.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;
    private final PatientService patientService;
    private final CounterService counterService;


    public DonationService(DonationRepository donationRepository, PatientService patientService, CounterService counterService) {
        this.donationRepository = donationRepository;
        this.patientService = patientService;
        this.counterService = counterService;
    }
    @Transactional(readOnly = true)
    public List<DonationDTO> findAll() {
        List<Donation> Donations = donationRepository.findAll();
        List<DonationDTO> donationDTOS = DonationFactory.DonationsToDonationDTO(Donations);

        return donationDTOS;
    }
    @Transactional(readOnly = true)
    public DonationDTO findDonationByCode(String code) {
        Donation donation = donationRepository.findByCode(code);
        Preconditions.checkBusinessLogique(donation != null,"error not find code");
        DonationDTO donationDTOS = DonationFactory.DonationToDonationDTO(donation);

        return donationDTOS;
    }

    public DonationDTO addDonation(DonationDTO donationDTO) {
        CounterDTO counter = counterService.findCounterByType("donation");
        donationDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        Donation donation = DonationFactory.DonationDTOToDonation(donationDTO);
        donation = donationRepository.save(donation);


        return DonationFactory.DonationToDonationDTO(donation);
    }


    public DonationDTO updateDonation(DonationDTO donationDTO){
        Donation donation = donationRepository.findByCode(donationDTO.getCode());
        Preconditions.checkBusinessLogique(donation!=null,"error donateur not exist");
        donationDTO.setCode(donation.getCode());
        donationDTO.setFullName(donation.getFullName());
        donationDTO.setPhoneNumber(donation.getPhoneNumber());
        donationDTO.setAge(donation.getAge());
        donationDTO.setSexe(donation.getSexe());
        donationDTO.setAdress(donation.getAdress());
        donationDTO.setDate_creation(donation.getDateCreate());
        donationDTO.setTypeIdentity(donation.getTypeIdentity());
        donationDTO.setNumIdentity(donation.getNumIdentity());
        Donation donation1 = donationRepository.save(DonationFactory.DonationDTOToDonation(donationDTO));
        return DonationFactory.DonationToDonationDTO(donation1);
    }
}