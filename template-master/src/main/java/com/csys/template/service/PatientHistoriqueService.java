package com.csys.template.service;

import com.csys.template.domain.donations_history;
import com.csys.template.dto.PatientHistoriqueDTO;
import com.csys.template.enumeration.StateEnum;
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
        List<donations_history> list = new ArrayList<>();
        for (donations_history patientHistorique : patientHistoriqueRepository.findAll()){
            if (patientHistorique.getCodePatient().equals(code)) {
                list.add(patientHistorique);
            }
        }
        return PatientHistoriqueFactory.patientHistoriquesToPatientHistoriqueDTOS(list);
    }

    @Transactional
    public donations_history addHistorique(PatientHistoriqueDTO patientHistoriqueDTO){
        Preconditions.checkArgument (patientHistoriqueDTO != null, "Patient added!");
        donations_history p = new donations_history();
        p.setState(StateEnum.PENDING.intValue());
        return patientHistoriqueRepository.save(PatientHistoriqueFactory.
                patientHisoriqueDTOToPatientHistorique(patientHistoriqueDTO));
    }

    public donations_history updateHistoriy(PatientHistoriqueDTO historyDTO){
        donations_history historyInDB = patientHistoriqueRepository.findByPatientCode(historyDTO.getCodePatient());
        Preconditions.checkArgument (historyInDB != null, "Patient has been updated");
        return patientHistoriqueRepository.save(PatientHistoriqueFactory.patientHisoriqueDTOToPatientHistorique(historyDTO));
    }


}
