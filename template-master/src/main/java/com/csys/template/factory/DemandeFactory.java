package com.csys.template.factory;

import com.csys.template.domain.Demande;
import com.csys.template.domain.DemandeHistory;
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
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setQuantiter(demande.getQuantiter());
        demandeDTO.setState(demande.getState());
        demandeDTO.setBlood(demande.getBlood());
        LocalDate d = demande.getCreateDate();
        String date= d.toString();
        demandeDTO.setCreateDate(date);
        demandeDTO.setUsercreate(demande.getUsercreate());
        Integer x = demande.getStatus();
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
                result = "PENDING";
                break;
        }
        demandeDTO.setStatus(result);

        return demandeDTO;

    }

    public static Demande demandeDTOToDemande(DemandeDTO demandeDTO){

        Demande demande= new Demande();
        LocalDate d = LocalDate.now();
        demande.setCode(demandeDTO.getCode());
        demande.setCodeMedecin(demandeDTO.getCodeMedecin());
        demande.setCodeService(demandeDTO.getCodeService());
        demande.setQuantiter(demandeDTO.getQuantiter());
        demande.setState(demandeDTO.getState());
        demande.setBlood(demandeDTO.getBlood());
        demande.setCreateDate(d);
        demande.setUsercreate(getUserAuthenticated());
        String ch = demandeDTO.getStatus();

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
                result = 3;
                break;
        }
        demande.setStatus(result);

        return demande ;
    }


    public static DemandeHistoryDTO demandeToDemandeHistoryDTO(Demande demande){
        DemandeHistoryDTO demandeDTO = new DemandeHistoryDTO();
        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setQuantiter(demande.getQuantiter());
        demandeDTO.setState(demande.getState());
        demandeDTO.setBlood(demande.getBlood());
        LocalDate d = demande.getCreateDate();
        String date= d.toString();
        demandeDTO.setCreateDate(date);
        demandeDTO.setUsercreate(demande.getUsercreate());
        Integer x = demande.getStatus();
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
                result = "PENDING";
                break;
        }
        demandeDTO.setStatus(result);

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
