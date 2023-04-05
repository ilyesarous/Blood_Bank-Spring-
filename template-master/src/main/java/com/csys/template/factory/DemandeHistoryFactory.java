package com.csys.template.factory;

import com.csys.template.domain.Demande;
import com.csys.template.domain.DemandeHistory;
import com.csys.template.dto.DemandeDTO;
import com.csys.template.dto.DemandeHistoryDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemandeHistoryFactory {

    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static DemandeHistoryDTO demandeHistoryToDemandeHistoryDTO(DemandeHistory demandeHistory){
        DemandeHistoryDTO demandeDTO = new DemandeHistoryDTO();
        demandeDTO.setCode(demandeHistory.getCode());
        demandeDTO.setCodeMedecin(demandeHistory.getCodeMedecin());
        demandeDTO.setCodeService(demandeHistory.getCodeService());
        demandeDTO.setQuantiter(demandeHistory.getQuantiter());
        demandeDTO.setState(demandeHistory.getState());
        demandeDTO.setBlood(demandeHistory.getBlood());
        LocalDate d = demandeHistory.getUpdateDate();
        String date= d.toString();
        demandeDTO.setUpdateDate(date);
        demandeDTO.setCreateDate(demandeHistory.getCreateDate());
        demandeDTO.setUsercreate(demandeHistory.getUsercreate());
        Integer x = demandeHistory.getStatus();
        String result = switch (x) {
            case 1 -> "SOLVED";
            case 2 -> "REJECTED";
            case 3 -> "PENDING";
            default -> "PENDING";
        };

        demandeDTO.setStatus(result);

        return demandeDTO;

    }

    public static DemandeHistory demandeHistoryDTOToDemandeHistory(DemandeHistoryDTO demandeDTO){

        DemandeHistory demande= new DemandeHistory();
        LocalDate d = LocalDate.now();
        demande.setCode(demandeDTO.getCode());
        demande.setCodeMedecin(demandeDTO.getCodeMedecin());
        demande.setCodeService(demandeDTO.getCodeService());
        demande.setQuantiter(demandeDTO.getQuantiter());
        demande.setState(demandeDTO.getState());
        demande.setBlood(demandeDTO.getBlood());
        demande.setUpdateDate(d);
        demande.setCreateDate(demandeDTO.getCreateDate());
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

    public static List<DemandeHistoryDTO> DemandesHistoryToDemandeHistorysDTO(List<DemandeHistory> demandes){
        List<DemandeHistoryDTO> demandeDTOS = new ArrayList<DemandeHistoryDTO>();
        for(DemandeHistory demande : demandes){
            demandeDTOS.add(demandeHistoryToDemandeHistoryDTO(demande));
        }
        return demandeDTOS;
    }

    public static List<DemandeHistory> DemandesHistoryDTOToDemandesHistory(List<DemandeHistoryDTO> demandeDTOS){
        List<DemandeHistory> demandes = new ArrayList<DemandeHistory>();
        for(DemandeHistoryDTO demandeDTO : demandeDTOS){
            demandes.add(demandeHistoryDTOToDemandeHistory(demandeDTO));

        }
        return demandes;
    }
}
