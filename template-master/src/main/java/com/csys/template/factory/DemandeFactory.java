package com.csys.template.factory;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Demande;
import com.csys.template.dto.DemandeDTO;
import com.csys.template.dto.DemandeHistoryDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemandeFactory {
    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static DemandeDTO demandeToDemandeDTO(Demande demande){
        DemandeDTO demandeDTO = new DemandeDTO();
        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setNameMedecin(demande.getNameMedecin());
        demandeDTO.setNameService(demande.getNameService());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setQuantiter(demande.getQuantiter());
        demandeDTO.setState(demande.getState());
        demandeDTO.setBlood(demande.getBlood().toString());
        LocalDate d = demande.getCreateDate();
        String date= d.toString();
        demandeDTO.setCreateDate(date);
        demandeDTO.setUsercreate(demande.getUsercreate());
        Integer x = demande.getStatus();
        String result = switch (x) {
            case 1 -> "SOLVED";
            case 2 -> "REJECTED";
            case 3 -> "PENDING";
            default -> "PENDING";
        };

        demandeDTO.setStatus(result);

        return demandeDTO;

    }

    public static Demande demandeDTOToDemande(DemandeDTO demandeDTO){

        Demande demande= new Demande();
        LocalDate d = LocalDate.now();
        demande.setCode(demandeDTO.getCode());
        demande.setCodeMedecin(demandeDTO.getCodeMedecin());
        demande.setNameMedecin(demandeDTO.getNameMedecin());
        demande.setNameService(demandeDTO.getNameService());
        demande.setCodeService(demandeDTO.getCodeService());
        demande.setQuantiter(demandeDTO.getQuantiter());
        demande.setState(demandeDTO.getState());

        demande.setBlood(Integer.parseInt(demandeDTO.getBlood()));
        demande.setCreateDate(d);
        demande.setUsercreate(getUserAuthenticated());
        String ch = demandeDTO.getStatus();

        Integer result = switch (ch) {
            case "SOLVED" -> 1;
            case "REJECTED" -> 2;
            case "PENDING" -> 3;
            default -> 3;
        };

        demande.setStatus(result);

        return demande ;
    }



    public static DemandeHistoryDTO demandeToDemandeHistoryDTO(DemandeDTO demande){
        DemandeHistoryDTO demandeDTO = new DemandeHistoryDTO();
        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setQuantiter(demande.getQuantiter());
        demandeDTO.setState(demande.getState());
        demandeDTO.setBlood(demande.getBlood());
        demandeDTO.setCreateDate(demande.getCreateDate());
        demandeDTO.setUsercreate(demande.getUsercreate());
        demandeDTO.setStatus(demande.getStatus());

        return demandeDTO;

    }

    public static List<DemandeDTO> stocksToStocksDTO(List<Demande> demandes){
        List<DemandeDTO> demandeDTOS = new ArrayList<DemandeDTO>();
        for(Demande demande : demandes){
            demandeDTOS.add(demandeToDemandeDTO(demande));
        }
        return demandeDTOS;
    }

    public static List<Demande> stocksDTOToStocks(List<DemandeDTO> demandeDTOS){
        List<Demande> demandes = new ArrayList<Demande>();
        for(DemandeDTO demandeDTO : demandeDTOS){
            demandes.add(demandeDTOToDemande(demandeDTO));

        }
        return demandes;
    }
}
