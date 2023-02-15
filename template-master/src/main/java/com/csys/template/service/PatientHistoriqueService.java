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
        List<PatientHistoriqueDTO> patientHistoriqueDTOList = PatientHistoriqueFactory.
                patientHistoriquesToPatientHistoriqueDTOS(patientHistoriqueRepository.findAll());
        List<PatientHistoriqueDTO> list = new ArrayList<>();
        for (PatientHistoriqueDTO patientHistoriqueDTO : patientHistoriqueDTOList){
            if (patientHistoriqueDTO.getCodePatient().equals(code)) {
                list.add(PatientHistoriqueFactory.patientHisoriqueToPatientHistoriqueDTO(
                        patientHistoriqueRepository.findByPatientCode(code)));
            }
        }
        return list;
    }

    @Transactional
    public PatientHistorique addHistorique(PatientHistoriqueDTO patientHistoriqueDTO){
        return patientHistoriqueRepository.save(PatientHistoriqueFactory.
                patientHisoriqueDTOToPatientHistorique(patientHistoriqueDTO));
    }

    public PatientHistorique updateHistoriy(PatientHistoriqueDTO historyDTO){
        PatientHistorique historyInDB = patientHistoriqueRepository.findByPatientCode(historyDTO.getCodePatient());
        Preconditions.checkArgument (historyInDB != null, "Patient has been updated");
        return patientHistoriqueRepository.save(PatientHistoriqueFactory.patientHisoriqueDTOToPatientHistorique(historyDTO));
    }


}
