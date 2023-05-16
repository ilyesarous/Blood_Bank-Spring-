package com.csys.template.factory;

import com.csys.template.domain.ReceiptBeforeDonation;
import com.csys.template.dto.ReceiptBeforeDonationDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceiptBeforeDonationFactory {

    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static ReceiptBeforeDonationDTO ReceiptbeforedonationToReceiptbeforeDonationDTO(ReceiptBeforeDonation receiptBeforeDonation) {
        ReceiptBeforeDonationDTO receiptBeforeDonationDTO = new ReceiptBeforeDonationDTO();
        receiptBeforeDonationDTO.setCodepatient(receiptBeforeDonation.getCodepatient());
        receiptBeforeDonationDTO.setBlood(receiptBeforeDonation.getBlood().toString());
        receiptBeforeDonationDTO.setUserCreate(receiptBeforeDonation.getUserCreate());
        receiptBeforeDonationDTO.setCode(receiptBeforeDonation.getCode());
        receiptBeforeDonationDTO.setId(receiptBeforeDonation.getId());

        LocalDate date=receiptBeforeDonation.getDateCreate();
        String d=date.toString();
        receiptBeforeDonationDTO.setDateCreate(d);

        Integer etat= receiptBeforeDonation.getEtat();

        String result = switch (etat) {
            case 1 -> "SOLVED";
            case 2 -> "REJECTED";
            case 3 -> "PENDING";
            default -> "PENDING";
        };
        receiptBeforeDonationDTO.setEtat(result);

        return receiptBeforeDonationDTO;
    }

    public static ReceiptBeforeDonation ReceiptbeforedonationDTOToReceiptbeforeDonation(ReceiptBeforeDonationDTO receiptBeforeDonationDTO) {
        ReceiptBeforeDonation receiptBeforeDonation = new ReceiptBeforeDonation();
        LocalDate date=LocalDate.now();

        receiptBeforeDonation.setCodepatient(receiptBeforeDonationDTO.getCodepatient());
        Integer blood=Integer.parseInt(receiptBeforeDonationDTO.getBlood());
        receiptBeforeDonation.setBlood(blood);
        receiptBeforeDonation.setUserCreate(getUserAuthenticated());
        receiptBeforeDonation.setCode(receiptBeforeDonationDTO.getCode());
        receiptBeforeDonation.setId(receiptBeforeDonationDTO.getId());

        receiptBeforeDonation.setDateCreate(date);

        String etat= receiptBeforeDonationDTO.getEtat();

        Integer result = switch (etat) {
            case "SOLVED" -> 1;
            case "REJECTED" -> 2;
            case "PENDING" -> 3;
            default -> 3;
        };
        receiptBeforeDonation.setEtat(result);
        return receiptBeforeDonation;
    }


    public static List<ReceiptBeforeDonationDTO> DonationsToDonationDTO(List<ReceiptBeforeDonation> receiptBeforeDonations) {
        List<ReceiptBeforeDonationDTO> receiptBeforeDonationDTOS = new ArrayList<ReceiptBeforeDonationDTO>();
        for (ReceiptBeforeDonation receiptBeforeDonation : receiptBeforeDonations) {
            receiptBeforeDonationDTOS.add(ReceiptbeforedonationToReceiptbeforeDonationDTO(receiptBeforeDonation));
        }
        return receiptBeforeDonationDTOS;
    }

    public static List<ReceiptBeforeDonation> patientsDTOToPatients(List<ReceiptBeforeDonationDTO> receiptBeforeDonationDTOS) {
        List<ReceiptBeforeDonation> receiptBeforeDonations = new ArrayList<ReceiptBeforeDonation>();
        for (ReceiptBeforeDonationDTO receiptBeforeDonationDTO : receiptBeforeDonationDTOS) {
            receiptBeforeDonations.add(ReceiptbeforedonationDTOToReceiptbeforeDonation(receiptBeforeDonationDTO));
        }
        return receiptBeforeDonations;
    }
}
