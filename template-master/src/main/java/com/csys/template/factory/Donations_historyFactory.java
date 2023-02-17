package com.csys.template.factory;

import com.csys.template.domain.Donations_history;
import com.csys.template.dto.Donations_historyDTO;
import com.csys.template.enumeration.StateEnum;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Donations_historyFactory {

    public static Donations_historyDTO patientHisoriqueToPatientHistoriqueDTO(Donations_history patientHistorique){
        Donations_historyDTO patientHistoriqueDTO = new Donations_historyDTO();
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

    public static Donations_history patientHisoriqueDTOToPatientHistorique(Donations_historyDTO patientHistoriqueDTO){
        Donations_history patientHistorique = new Donations_history();

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

    public static List<Donations_historyDTO> patientHistoriquesToPatientHistoriqueDTOS(List<Donations_history> list){
        List<Donations_historyDTO> patientHistoriqueDTOList = new ArrayList<>();
        for (Donations_history patientHistorique : list){
            patientHistoriqueDTOList.add(patientHisoriqueToPatientHistoriqueDTO(patientHistorique));
        }
        return patientHistoriqueDTOList;
    }

}
