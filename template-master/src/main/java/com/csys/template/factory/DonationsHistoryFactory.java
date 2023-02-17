package com.csys.template.factory;

import com.csys.template.domain.DonationsHistory;
import com.csys.template.dto.DonationsHistoryDTO;
import com.csys.template.enumeration.StateEnum;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonationsHistoryFactory {

    public static DonationsHistoryDTO patientHisoriqueToPatientHistoriqueDTO(DonationsHistory patientHistorique){
        DonationsHistoryDTO patientHistoriqueDTO = new DonationsHistoryDTO();
        patientHistoriqueDTO.setCodePatient(patientHistorique.getCodePatient());
        patientHistoriqueDTO.setCode(patientHistorique.getCode());
        String i = switch (patientHistorique.getState()) {
            case 3 -> "pending";
            case 2 -> "rejected";
            case 1 -> "solved";
            default -> null;
        };
        patientHistoriqueDTO.setState(i);
        patientHistoriqueDTO.setObservation(patientHistorique.getObservation());
        patientHistoriqueDTO.setDateCreate(patientHistorique.getDateCreate().getTime());
        patientHistoriqueDTO.setUserCreate(patientHistorique.getUserCreate());

        return patientHistoriqueDTO;
    }

    public static DonationsHistory patientHisoriqueDTOToPatientHistorique(DonationsHistoryDTO patientHistoriqueDTO){
        DonationsHistory patientHistorique = new DonationsHistory();

        patientHistorique.setCodePatient(patientHistoriqueDTO.getCodePatient());
        patientHistorique.setCode(patientHistoriqueDTO.getCode());
        int i = switch (patientHistoriqueDTO.getState()) {
            case "pending" -> StateEnum.PENDING.intValue();
            case "rejected" -> StateEnum.REJECTED.intValue();
            case "solved" -> StateEnum.SOLVED.intValue();
            default -> 0;
        };
        Preconditions.checkArgument (i != 0, "verify the state of the patient");
        patientHistorique.setState(i);
        patientHistorique.setObservation(patientHistoriqueDTO.getObservation());
        patientHistorique.setDateCreate(new Date(patientHistoriqueDTO.getDateCreate()));
        patientHistorique.setUserCreate(patientHistoriqueDTO.getUserCreate());

        return patientHistorique;
    }

    public static List<DonationsHistoryDTO> patientHistoriquesToPatientHistoriqueDTOS(List<DonationsHistory> list){
        List<DonationsHistoryDTO> patientHistoriqueDTOList = new ArrayList<>();
        for (DonationsHistory patientHistorique : list){
            patientHistoriqueDTOList.add(patientHisoriqueToPatientHistoriqueDTO(patientHistorique));
        }
        return patientHistoriqueDTOList;
    }

}
