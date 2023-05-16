package com.csys.template.service;


import com.csys.template.domain.Donation;
import com.csys.template.domain.Patient;
import com.csys.template.dto.*;
import com.csys.template.factory.DonationFactory;
import com.csys.template.repository.DonationRepository;

import com.csys.template.util.Preconditions;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.csys.template.TemplateApplication.log;


@Service
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;
    private final CounterService counterService;
    private final DonationHistoryService donationHistoryService;
    private final StockService stockService;
    private final PatientService patientService;


    public DonationService(DonationRepository donationRepository, PatientService patientService, CounterService counterService, DonationHistoryService donationHistoryService, StockService stockService, PatientService patientService1) {
        this.donationRepository = donationRepository;
        this.counterService = counterService;
        this.donationHistoryService = donationHistoryService;
        this.stockService = stockService;
        this.patientService = patientService1;
    }
    @Transactional(readOnly = true)
    public DonationDTO findByCode(String code) {
        log.debug("***find Donation By Code ***");
        Donation donation = donationRepository.findByCode(code);
        Preconditions.checkBusinessLogique(donation != null,"donor does  Not found!");
        DonationDTO donationDTOS = DonationFactory.DonationToDonationDTO(donation);

        return donationDTOS;
    }
    @Transactional(readOnly = true)

    public List<DonationDTO> GetAll(Specification<Donation> donation) {
        log.debug("*** Get All donation ***");
       List <Donation> donations = donationRepository.findAll(donation);


       List <DonationDTO> donationDTOS = DonationFactory.DonationsToDonationDTO(donations);

        return donationDTOS;
    }

    public DonationDTO addDonation(DonationDTO donationDTO) {
        log.debug("*** add Donation ***");
        CounterDTO counter = counterService.findCounterByType("donation");
        donationDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        donationDTO.setObservation("---");
        Donation donation = DonationFactory.DonationDTOToDonation(donationDTO);
        DonationsHistoryDTO donationsHistoryDTO= DonationFactory.DonationDTOToDonationHistory(donationDTO);
        DonationsHistoryDTO donationsHistory= donationHistoryService.addHistorique(donationsHistoryDTO);
        donation = donationRepository.save(donation);


        return DonationFactory.DonationToDonationDTO(donation);
    }


    public DonationDTO updateDonation(DonationDTO donationDTO){
        log.debug("*** update Donation ***");
        Preconditions.checkBusinessLogique(donationDTO.getObservation()!=null,"error.couldn't-find-observation");
        Donation donation = donationRepository.findByCode(donationDTO.getCode());
        Preconditions.checkBusinessLogique(donation!=null,"donor does  Not found!");
        donationDTO.setCode(donation.getCode());
        donationDTO.setFullName(donation.getFullName());
        donationDTO.setCodePatient(donation.getCodepatient());
        donationDTO.setPhoneNumber(donation.getPhoneNumber());
        donationDTO.setTension(donation.getTension());
        donationDTO.setSexe(donation.getSexe());
        donationDTO.setAdress(donation.getAdress());

        if (donationDTO.getEtat().equals("SOLVED"))
        {
            StockDTO stockDTO= DonationFactory.DonationDTOToStockDTO(donationDTO);
            stockService.addStock(stockDTO);
        }
        PatientDTO patientDTO=patientService.findByCode(donationDTO.getCodePatient());

        Preconditions.checkBusinessLogique(patientDTO!=null,"donor does  Not found!"+patientDTO.getBloodCode());
        if(patientDTO.getBloodCode().equals("--"))
        {
            patientDTO.setBloodCode(donationDTO.getBlood());
            patientService.updateBloodPatient(patientDTO);
        }
        DonationsHistoryDTO donationsHistoryDTO= DonationFactory.DonationDTOToDonationHistory(donationDTO);
        donationHistoryService.addHistorique(donationsHistoryDTO);
        Donation donation1 = donationRepository.save(DonationFactory.DonationDTOToDonation(donationDTO));
        return DonationFactory.DonationToDonationDTO(donation1);
    }
}
