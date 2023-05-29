package com.csys.template.factory;

import com.csys.template.domain.DonationsHistory;
import com.csys.template.dto.DonationsHistoryDTO;
import com.csys.template.enumeration.StateEnum;
import com.google.common.base.Preconditions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonationsHistoryFactory {

    public static DonationsHistoryDTO patientHisoriqueToPatientHistoriqueDTO(DonationsHistory patientHistorique){
        DonationsHistoryDTO patientHistoriqueDTO = new DonationsHistoryDTO();
        LocalDate d=patientHistorique.getDateCreate();
        String x=d.toString();
        patientHistoriqueDTO.setId(patientHistorique.getId());
        patientHistoriqueDTO.setCodePatient(patientHistorique.getCodePatient());
        patientHistoriqueDTO.setCode(patientHistorique.getCode());
        String i = switch (patientHistorique.getState()) {
            case 3 -> "PENDING";
            case 2 -> "REJECTED";
            case 1 -> "SOLVED";
            default -> null;
        };
        patientHistoriqueDTO.setState(i);
        patientHistoriqueDTO.setObservation(patientHistorique.getObservation());
        patientHistoriqueDTO.setDateCreate(x);
        patientHistoriqueDTO.setDiastolicPressure(patientHistorique.getDiastolicPressure());
        patientHistoriqueDTO.setSystolicPressure(patientHistorique.getSystolicPressure());
        patientHistoriqueDTO.setUserCreate(patientHistorique.getUserCreate());

        return patientHistoriqueDTO;
    }

    public static DonationsHistory patientHisoriqueDTOToPatientHistorique(DonationsHistoryDTO patientHistoriqueDTO){
        DonationsHistory patientHistorique = new DonationsHistory();
        LocalDate d = LocalDate.now();
        patientHistorique.setId(patientHistoriqueDTO.getId());
        patientHistorique.setCode(patientHistoriqueDTO.getCode());
        patientHistorique.setCodePatient(patientHistoriqueDTO.getCodePatient());

        int i = switch (patientHistoriqueDTO.getState()) {
            case "PENDING" -> StateEnum.PENDING.intValue();
            case "REJECTED" -> StateEnum.REJECTED.intValue();
            case "SOLVED" -> StateEnum.SOLVED.intValue();
            default -> StateEnum.REJECTED.intValue();
        };

        patientHistorique.setState(i);
        patientHistorique.setObservation(patientHistoriqueDTO.getObservation());
        patientHistorique.setDiastolicPressure(patientHistoriqueDTO.getDiastolicPressure());
        patientHistorique.setSystolicPressure(patientHistoriqueDTO.getSystolicPressure());
        patientHistorique.setDateCreate(d);
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
