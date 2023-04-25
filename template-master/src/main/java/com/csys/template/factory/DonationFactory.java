package com.csys.template.factory;


import com.csys.template.domain.Donation;
import com.csys.template.dto.DonationDTO;
import com.csys.template.dto.DonationsHistoryDTO;
import com.csys.template.dto.StockDTO;
import com.csys.template.service.StateService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonationFactory {
    private final StateService stateService;

    public DonationFactory(StateService stateService) {
        this.stateService = stateService;
    }


    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static DonationDTO DonationToDonationDTO(Donation donation) {
        DonationDTO donationDTO = new DonationDTO();
        LocalDate d = donation.getDateCreate();
        String b = d.toString();

        donationDTO.setCode(donation.getCode());
        donationDTO.setFullName(donation.getFullName());
        donationDTO.setCodePatient(donation.getCodepatient());
        donationDTO.setAge(donation.getAge());
        donationDTO.setAdress(donation.getAdress());
        donationDTO.setSexe(donation.getSexe());
        donationDTO.setTypeIdentity(donation.getTypeIdentity());
        donationDTO.setNumIdentity(donation.getNumIdentity());
        donationDTO.setPhoneNumber(donation.getPhoneNumber());

        donationDTO.setDate_creation(b);
        donationDTO.setDatecreationLd(donation.getDateCreate());

        donationDTO.setUserCreate(donation.getUserCreate());
        donationDTO.setBlood(donation.getBlood());
        donationDTO.setObservation(donation.getObservation());
        Integer x = donation.getEtat();
        String result = switch (x) {
            case 1 -> "SOLVED";
            case 2 -> "REJECTED";
            case 3 -> "PENDING";
            default -> "PENDING";
        };

        donationDTO.setEtat(result);


        return donationDTO;
    }

    public static Donation DonationDTOToDonation(DonationDTO donationDTO) {
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
        donation.setUserCreate(getUserAuthenticated());
        donation.setBlood(donationDTO.getBlood());
        donation.setObservation(donationDTO.getObservation());
        String ch = donationDTO.getEtat();

        Integer result = switch (ch) {
            case "SOLVED" -> 1;
            case "REJECTED" -> 2;
            case "PENDING" -> 3;
            default -> 3;
        };

        donation.setEtat(result);


        return donation;
    }

    public static DonationsHistoryDTO DonationDTOToDonationHistory(DonationDTO donationDTO) {
        DonationsHistoryDTO donation = new DonationsHistoryDTO();
        donation.setCode(donationDTO.getCode());
        donation.setCodePatient(donationDTO.getCodePatient());
        donation.setState(donationDTO.getEtat());
        donation.setObservation(donationDTO.getObservation());
        donation.setUserCreate(getUserAuthenticated());


        return donation;
    }

    public static StockDTO DonationDTOToStockDTO(DonationDTO donationDTO) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setCodedonateur(donationDTO.getCode());
        stockDTO.setBlood(donationDTO.getBlood());
        stockDTO.setQuantite(1);


        return stockDTO;
    }

    public static List<DonationDTO> DonationsToDonationDTO(List<Donation> donations) {
        List<DonationDTO> donationDTOS = new ArrayList<DonationDTO>();
        for (Donation donation : donations) {
            donationDTOS.add(DonationToDonationDTO(donation));
        }
        return donationDTOS;
    }

    public static List<Donation> patientsDTOToPatients(List<DonationDTO> donationDTOS) {
        List<Donation> donations = new ArrayList<Donation>();
        for (DonationDTO patient : donationDTOS) {
            donations.add(DonationDTOToDonation(patient));
        }
        return donations;
    }
}
