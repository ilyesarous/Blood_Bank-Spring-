package com.csys.template.factory;

import com.csys.template.domain.ReceiptAfterDonation;
import com.csys.template.domain.ReceiptBeforeDonation;
import com.csys.template.dto.ReceiptAfterDonationDTO;
import com.csys.template.dto.ReceiptBeforeDonationDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceiptAfterDonationFactory {

    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static ReceiptAfterDonationDTO ReceiptAfterdonationToReceiptAfterDonationDTO(ReceiptAfterDonation receiptAfterDonation) {
        ReceiptAfterDonationDTO receiptAfterDonationDTO = new ReceiptAfterDonationDTO();
        receiptAfterDonationDTO.setCodepatient(receiptAfterDonation.getCodepatient());
        receiptAfterDonationDTO.setBlood(receiptAfterDonation.getBlood().toString());
        receiptAfterDonationDTO.setUserCreate(receiptAfterDonation.getUserCreate());
        receiptAfterDonationDTO.setCode(receiptAfterDonation.getCode());
        receiptAfterDonationDTO.setId(receiptAfterDonation.getId());
        receiptAfterDonationDTO.setDateperime(receiptAfterDonation.getDateperime());

        LocalDate date=receiptAfterDonation.getDateCreate();
        String d=date.toString();
        receiptAfterDonationDTO.setDateCreate(d);

        Integer etat= receiptAfterDonation.getEtat();

        String result = switch (etat) {
            case 1 -> "SOLVED";
            case 2 -> "REJECTED";
            case 3 -> "PENDING";
            default -> "PENDING";
        };
        receiptAfterDonationDTO.setEtat(result);

        return receiptAfterDonationDTO;
    }

    public static ReceiptAfterDonation ReceiptbeforedonationDTOToReceiptbeforeDonation(ReceiptAfterDonationDTO receiptAfterDonationDTO) {
        ReceiptAfterDonation receiptAfterDonation = new ReceiptAfterDonation();
        LocalDate date=LocalDate.now();
        String  x =date.toString();
        String [] tab = x.split("-");
        int y= Integer.parseInt(tab[1]);
        int z=Integer.parseInt(tab[0]);
        y=y+3;
        if (y>12)
        {
            y=y-12;
            z=z+1;
        }
        tab[0]= Integer.toString(z);
        tab[1]= Integer.toString(y);
        String v= tab[0]+"-"+tab[1]+"-"+tab[2];
        receiptAfterDonation.setDateperime(v);
        receiptAfterDonation.setCodepatient(receiptAfterDonationDTO.getCodepatient());
        Integer blood=Integer.parseInt(receiptAfterDonationDTO.getBlood());
        receiptAfterDonation.setBlood(blood);
        receiptAfterDonation.setUserCreate(getUserAuthenticated());
        receiptAfterDonation.setCode(receiptAfterDonationDTO.getCode());
        receiptAfterDonation.setId(receiptAfterDonationDTO.getId());

        receiptAfterDonation.setDateCreate(date);

        String etat= receiptAfterDonationDTO.getEtat();

        Integer result = switch (etat) {
            case "SOLVED" -> 1;
            case "REJECTED" -> 2;
            case "PENDING" -> 3;
            default -> 3;
        };
        receiptAfterDonation.setEtat(result);
        return receiptAfterDonation;
    }


    public static List<ReceiptAfterDonationDTO> DonationsToDonationDTO(List<ReceiptAfterDonation> receiptAfterDonations) {
        List<ReceiptAfterDonationDTO> receiptAfterDonationDTOS = new ArrayList<ReceiptAfterDonationDTO>();
        for (ReceiptAfterDonation receiptAfterDonation : receiptAfterDonations) {
            receiptAfterDonationDTOS.add(ReceiptAfterdonationToReceiptAfterDonationDTO(receiptAfterDonation));
        }
        return receiptAfterDonationDTOS;
    }

    public static List<ReceiptAfterDonation> patientsDTOToPatients(List<ReceiptAfterDonationDTO> receiptAfterDonationDTOS) {
        List<ReceiptAfterDonation> receiptAfterDonations = new ArrayList<ReceiptAfterDonation>();
        for (ReceiptAfterDonationDTO receiptAfterDonationDTO : receiptAfterDonationDTOS) {
            receiptAfterDonations.add(ReceiptbeforedonationDTOToReceiptbeforeDonation(receiptAfterDonationDTO));
        }
        return receiptAfterDonations ;
    }
}
