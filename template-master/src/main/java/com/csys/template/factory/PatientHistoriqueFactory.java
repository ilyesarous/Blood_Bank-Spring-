package com.csys.template.factory;

import com.csys.template.domain.donations_history;
import com.csys.template.dto.PatientHistoriqueDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientHistoriqueFactory {

    public static PatientHistoriqueDTO patientHisoriqueToPatientHistoriqueDTO(donations_history patientHistorique){
        PatientHistoriqueDTO patientHistoriqueDTO = new PatientHistoriqueDTO();
        patientHistoriqueDTO.setCodePatient(patientHistorique.getCodePatient());
        patientHistoriqueDTO.setCode(patientHistorique.getCode());
        patientHistoriqueDTO.setState(patientHistorique.getState());
        patientHistoriqueDTO.setObservation(patientHistorique.getObservation());
        patientHistoriqueDTO.setDateCreate(patientHistorique.getDateCreate().getTime());
        patientHistoriqueDTO.setUserCreate(patientHistorique.getUserCreate());

        return patientHistoriqueDTO;
    }

    public static donations_history patientHisoriqueDTOToPatientHistorique(PatientHistoriqueDTO patientHistoriqueDTO){
        donations_history patientHistorique = new donations_history();
        patientHistorique.setCodePatient(patientHistoriqueDTO.getCodePatient());
        patientHistorique.setCode(patientHistoriqueDTO.getCode());
        patientHistorique.setState(patientHistoriqueDTO.getState());
        patientHistorique.setObservation(patientHistoriqueDTO.getObservation());
        patientHistorique.setDateCreate(new Date(patientHistoriqueDTO.getDateCreate()));
        patientHistorique.setUserCreate(patientHistoriqueDTO.getUserCreate());

        return patientHistorique;
    }

    public static List<PatientHistoriqueDTO> patientHistoriquesToPatientHistoriqueDTOS(List<donations_history> list){
        List<PatientHistoriqueDTO> patientHistoriqueDTOList = new ArrayList<>();
        for (donations_history patientHistorique : list){
            patientHistoriqueDTOList.add(patientHisoriqueToPatientHistoriqueDTO(patientHistorique));
        }
        return patientHistoriqueDTOList;
    }

}
