package com.csys.template.service;

import com.csys.template.domain.PatientHistorique;
import com.csys.template.dto.PatientHistoriqueDTO;
import com.csys.template.factory.PatientHistoriqueFactory;
import com.csys.template.repository.PatientHistoriqueRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientHistoriqueService {

    private final PatientHistoriqueRepository patientHistoriqueRepository;

    public PatientHistoriqueService(PatientHistoriqueRepository patientHistoriqueRepository) {
        this.patientHistoriqueRepository = patientHistoriqueRepository;
    }

    @Transactional(readOnly = true)
    public List<PatientHistoriqueDTO> findHistory(String code){
        List<PatientHistorique> list = new ArrayList<>();
        for (PatientHistorique patientHistorique : patientHistoriqueRepository.findAll()){
            if (patientHistorique.getCodePatient().equals(code)) {
                list.add(patientHistorique);
            }
        }
        return PatientHistoriqueFactory.patientHistoriquesToPatientHistoriqueDTOS(list);
    }

    @Transactional
    public PatientHistorique addHistorique(PatientHistoriqueDTO patientHistoriqueDTO){
        Preconditions.checkArgument (patientHistoriqueDTO != null, "Patient added!");
        return patientHistoriqueRepository.save(PatientHistoriqueFactory.
                patientHisoriqueDTOToPatientHistorique(patientHistoriqueDTO));
    }

    public PatientHistorique updateHistoriy(PatientHistoriqueDTO historyDTO){
        PatientHistorique historyInDB = patientHistoriqueRepository.findByPatientCode(historyDTO.getCodePatient());
        Preconditions.checkArgument (historyInDB != null, "Patient has been updated");
        return patientHistoriqueRepository.save(PatientHistoriqueFactory.patientHisoriqueDTOToPatientHistorique(historyDTO));
    }


}
