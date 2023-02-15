package com.csys.template.service;

import com.csys.template.domain.PatientHistorique;
import com.csys.template.dto.PatientHistoriqueDTO;
import com.csys.template.factory.PatientHistoriqueFactory;
import com.csys.template.repository.PatientHistoriqueRepository;

import java.util.ArrayList;
import java.util.List;

public class PatientHistoriqueService {

    private final PatientHistoriqueRepository patientHistoriqueRepository;

    public PatientHistoriqueService(PatientHistoriqueRepository patientHistoriqueRepository) {
        this.patientHistoriqueRepository = patientHistoriqueRepository;
    }

    public List<PatientHistoriqueDTO> findByPatientCode(String patientCode){
        List<PatientHistoriqueDTO> patientHistoriqueDTOList = PatientHistoriqueFactory.
                patientHistoriquesToPatientHistoriqueDTOS(patientHistoriqueRepository.findAll());
        List<PatientHistoriqueDTO> list = new ArrayList<PatientHistoriqueDTO>();
        for (PatientHistoriqueDTO patientHistoriqueDTO : patientHistoriqueDTOList){
            if (patientHistoriqueDTO.getCodePatient().equals(patientCode))
                list.add(PatientHistoriqueFactory.patientHisoriqueToPatientHistoriqueDTO(
                        patientHistoriqueRepository.findByPatientCode(patientCode)));
        }
        return list;
    }

    public PatientHistorique addHistorique(PatientHistoriqueDTO patientHistoriqueDTO){
        return patientHistoriqueRepository.save(PatientHistoriqueFactory.
                patientHisoriqueDTOToPatientHistorique(patientHistoriqueDTO));
    }


}
