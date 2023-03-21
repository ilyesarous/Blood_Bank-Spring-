package com.csys.template.factory;


import com.csys.template.domain.Blood;
import com.csys.template.domain.Donation;
import com.csys.template.domain.DonationsHistory;
import com.csys.template.domain.State;
import com.csys.template.dto.DonationDTO;
import com.csys.template.dto.DonationsHistoryDTO;
import com.csys.template.enumeration.StateEnum;
import com.csys.template.service.StateService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonationFactory {
    private final StateService stateService;

    public DonationFactory(StateService stateService) {
        this.stateService = stateService;
    }

    public static DonationDTO DonationToDonationDTO(Donation donation){
        DonationDTO donationDTO = new DonationDTO();


        donationDTO.setCode(donation.getCode());
        donationDTO.setFullName(donation.getFullName());
        donationDTO.setCodePatient(donation.getCodepatient());
        donationDTO.setAge(donation.getAge());
        donationDTO.setAdress(donation.getAdress());
        donationDTO.setSexe(donation.getSexe());
        donationDTO.setTypeIdentity(donation.getTypeIdentity());
        donationDTO.setNumIdentity(donation.getNumIdentity());
        donationDTO.setPhoneNumber(donation.getPhoneNumber());
        donationDTO.setDate_creation(donation.getDateCreate());
        Integer x = donation.getEtat();
        String result;

        switch (x) {
            case 1 :
                result = "SOLVED" ;
                break;
            case 2:
                result = "REJECTED";
                break;
            case 3 :
                result = "PENDING";
                break;
            default:
                result = "REJECTED";
                break;
        }
        donationDTO.setEtat(result);



        return donationDTO;
    }

    public static Donation DonationDTOToDonation(DonationDTO donationDTO){
        Donation donation = new Donation();
        LocalDate d = LocalDate.now();


        donation.setCode(donationDTO.getCode());
        donation.setFullName(donationDTO.getFullName());
        donation.setCodepatient(donationDTO.getCodePatient());
        donation.setTypeIdentity(donationDTO.getTypeIdentity());
        donation.setNumIdentity(donationDTO.getNumIdentity());
        donation.setAge(donationDTO.getAge());
        donation.setAdress(donationDTO.getAdress());
        donation.setSexe(donationDTO.getSexe());
        donation.setPhoneNumber(donationDTO.getPhoneNumber());
        donation.setDateCreate(d);
        String ch = donationDTO.getEtat();
        Integer result;

        switch (ch) {
            case  "SOLVED":
                result = 1 ;
                break;
            case "REJECTED":
                result = 2;
                break;
            case "PENDING":
                result = 3;
                break;
            default:
                result = 2;
                break;
        }
        donation.setEtat(result);


        return donation;
    }
    public static DonationsHistoryDTO DonationDTOToDonationHistory(DonationDTO donationDTO){
        DonationsHistoryDTO donation = new DonationsHistoryDTO();
        donation.setCode(donationDTO.getCode());
        donation.setCodePatient(donationDTO.getCodePatient());
        donation.setState(donationDTO.getEtat());
        donation.setObservation("aya");
        donation.setUserCreate("csys");




        return donation;
    }

    public static List<DonationDTO> DonationsToDonationDTO(List<Donation> donations){
        List<DonationDTO> donationDTOS = new ArrayList<DonationDTO>();
        for(Donation donation : donations){
            donationDTOS.add(DonationToDonationDTO(donation));
        }
        return donationDTOS;
    }

    public static List<Donation> patientsDTOToPatients(List<DonationDTO> donationDTOS){
        List<Donation> donations = new ArrayList<Donation>();
        for(DonationDTO patient : donationDTOS){
            donations.add(DonationDTOToDonation(patient));
        }
        return donations;
    }
}
