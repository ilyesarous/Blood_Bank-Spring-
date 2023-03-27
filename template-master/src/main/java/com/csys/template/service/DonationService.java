package com.csys.template.service;


import com.csys.template.domain.Donation;
import com.csys.template.domain.DonationsHistory;
import com.csys.template.domain.Patient;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.DonationDTO;
import com.csys.template.dto.DonationsHistoryDTO;
import com.csys.template.factory.DonationFactory;
import com.csys.template.repository.DonationRepository;

import com.csys.template.util.Preconditions;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;
    private final PatientService patientService;
    private final CounterService counterService;
    private final DonationHistoryService donationHistoryService;


    public DonationService(DonationRepository donationRepository, PatientService patientService, CounterService counterService, DonationHistoryService donationHistoryService) {
        this.donationRepository = donationRepository;
        this.patientService = patientService;
        this.counterService = counterService;
        this.donationHistoryService = donationHistoryService;
    }
//    @Transactional(readOnly = true)
//    public List<DonationDTO> findAll() {
//        List<Donation> Donations = donationRepository.findAll();
//        List<DonationDTO> donationDTOS = DonationFactory.DonationsToDonationDTO(Donations);
//
//        return donationDTOS;
//    }
    @Transactional(readOnly = true)
    public DonationDTO findDonationByCode(String code) {
        Donation donation = donationRepository.findByCode(code);
        Preconditions.checkBusinessLogique(donation != null,"donor does  Not found!");
        DonationDTO donationDTOS = DonationFactory.DonationToDonationDTO(donation);

        return donationDTOS;
    }
    @Transactional(readOnly = true)

    public List<DonationDTO> GetAll(Specification<Donation> donation) {
       List <Donation> donations = donationRepository.findAll(donation);

       List <DonationDTO> donationDTOS = DonationFactory.DonationsToDonationDTO(donations);

        return donationDTOS;
    }

    public DonationDTO addDonation(DonationDTO donationDTO) {
        CounterDTO counter = counterService.findCounterByType("donation");
        donationDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        Donation donation = DonationFactory.DonationDTOToDonation(donationDTO);
        DonationsHistoryDTO donationsHistoryDTO= DonationFactory.DonationDTOToDonationHistory(donationDTO);
        DonationsHistoryDTO donationsHistory= donationHistoryService.addHistorique(donationsHistoryDTO);
        donation = donationRepository.save(donation);


        return DonationFactory.DonationToDonationDTO(donation);
    }


    public DonationDTO updateDonation(DonationDTO donationDTO){
        Donation donation = donationRepository.findByCode(donationDTO.getCode());
        Preconditions.checkBusinessLogique(donation!=null,"error donateur not exist");
        donationDTO.setCode(donation.getCode());
        donationDTO.setFullName(donation.getFullName());
        donationDTO.setCodePatient(donation.getCodepatient());
        donationDTO.setPhoneNumber(donation.getPhoneNumber());
        donationDTO.setAge(donation.getAge());
        donationDTO.setSexe(donation.getSexe());
        donationDTO.setAdress(donation.getAdress());

        donationDTO.setTypeIdentity(donation.getTypeIdentity());
        donationDTO.setNumIdentity(donation.getNumIdentity());
        DonationsHistoryDTO donationsHistoryDTO= DonationFactory.DonationDTOToDonationHistory(donationDTO);
         donationHistoryService.addHistorique(donationsHistoryDTO);
        Donation donation1 = donationRepository.save(DonationFactory.DonationDTOToDonation(donationDTO));
        return DonationFactory.DonationToDonationDTO(donation1);
    }
}
