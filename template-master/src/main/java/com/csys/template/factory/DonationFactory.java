package com.csys.template.factory;


import com.csys.template.domain.Donation;
import com.csys.template.dto.DonationDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonationFactory {
    public static DonationDTO DonationToDonationDTO(Donation donation){
        DonationDTO donationDTO = new DonationDTO();


        donationDTO.setCode(donation.getCode());
        donationDTO.setFullName(donation.getFullName());
        donationDTO.setAge(donation.getAge());
        donationDTO.setAdress(donation.getAdress());
        donationDTO.setSexe(donation.getSexe());
        donationDTO.setTypeIdentity(donation.getTypeIdentity());
        donationDTO.setNumIdentity(donation.getNumIdentity());
        donationDTO.setPhoneNumber(donation.getPhoneNumber());
        donationDTO.setDate_creation(donation.getDateCreate());
        donationDTO.setEtat(donation.getEtat());


        return donationDTO;
    }

    public static Donation DonationDTOToDonation(DonationDTO donationDTO){
        Donation donation = new Donation();
        LocalDate d = LocalDate.now();


        donation.setCode(donationDTO.getCode());
        donation.setFullName(donationDTO.getFullName());
        donation.setTypeIdentity(donationDTO.getTypeIdentity());
        donation.setNumIdentity(donationDTO.getNumIdentity());
        donation.setAge(donationDTO.getAge());
        donation.setAdress(donationDTO.getAdress());
        donation.setSexe(donationDTO.getSexe());
        donation.setPhoneNumber(donationDTO.getPhoneNumber());
        donation.setDateCreate(d);
        donation.setEtat(donationDTO.getEtat());


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
