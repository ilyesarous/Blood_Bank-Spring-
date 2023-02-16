package com.csys.template.factory;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Patient;
import com.csys.template.domain.donations_history;
import com.csys.template.dto.PatientDTO;
import com.csys.template.dto.donations_historyDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class donations_historyFactory {

    public static donations_historyDTO patientHisoriqueToPatientHistoriqueDTO(donations_history patientHistorique){
        donations_historyDTO patientHistoriqueDTO = new donations_historyDTO();
        patientHistoriqueDTO.setCodePatient(patientHistorique.getCodePatient().getCode());
        patientHistoriqueDTO.setCode(patientHistorique.getCode());
        patientHistoriqueDTO.setState(patientHistorique.getState());
        patientHistoriqueDTO.setObservation(patientHistorique.getObservation());
        patientHistoriqueDTO.setDateCreate(patientHistorique.getDateCreate().getTime());
        patientHistoriqueDTO.setUserCreate(patientHistorique.getUserCreate());

        return patientHistoriqueDTO;
    }

    public static donations_history patientHisoriqueDTOToPatientHistorique(donations_historyDTO patientHistoriqueDTO){
        donations_history patientHistorique = new donations_history();
        Patient patient = new Patient();
        patient.setCode(patientHistoriqueDTO.getCodePatient());
        patientHistorique.setCodePatient(patient);
        patientHistorique.setCode(patientHistoriqueDTO.getCode());
        patientHistorique.setState(patientHistoriqueDTO.getState());
        patientHistorique.setObservation(patientHistoriqueDTO.getObservation());
        patientHistorique.setDateCreate(new Date(patientHistoriqueDTO.getDateCreate()));
        patientHistorique.setUserCreate(patientHistoriqueDTO.getUserCreate());

        return patientHistorique;
    }

    public static List<donations_historyDTO> patientHistoriquesToPatientHistoriqueDTOS(List<donations_history> list){
        List<donations_historyDTO> patientHistoriqueDTOList = new ArrayList<>();
        for (donations_history patientHistorique : list){
            patientHistoriqueDTOList.add(patientHisoriqueToPatientHistoriqueDTO(patientHistorique));
        }
        return patientHistoriqueDTOList;
    }

}
