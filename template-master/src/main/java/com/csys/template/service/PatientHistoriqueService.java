package com.csys.template.service;

import com.csys.template.domain.Donations_history;
import com.csys.template.dto.Donations_historyDTO;
import com.csys.template.enumeration.StateEnum;
import com.csys.template.factory.Donations_historyFactory;
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
    public List<Donations_historyDTO> findHistory(String code){
        List<Donations_history> list = new ArrayList<>();
        for (Donations_history patientHistorique : patientHistoriqueRepository.findAll()){
            if (patientHistorique.getCodePatient().equals(code)) {
                list.add(patientHistorique);
            }
        }
        return Donations_historyFactory.patientHistoriquesToPatientHistoriqueDTOS(list);
    }

    @Transactional
    public Donations_history addHistorique(Donations_historyDTO patientHistoriqueDTO){
        Preconditions.checkArgument (patientHistoriqueDTO != null, "Patient added!");
        Donations_history p = new Donations_history();
        p.setState(StateEnum.PENDING.intValue());
        return patientHistoriqueRepository.save(Donations_historyFactory.
                patientHisoriqueDTOToPatientHistorique(patientHistoriqueDTO));
    }

    public Donations_history updateHistoriy(Donations_historyDTO historyDTO){
        Donations_history historyInDB = patientHistoriqueRepository.findByPatientCode(historyDTO.getCodePatient());
        Preconditions.checkArgument (historyInDB != null, "Patient has been updated");
        return patientHistoriqueRepository.save(Donations_historyFactory.patientHisoriqueDTOToPatientHistorique(historyDTO));
    }


}
