package com.csys.template.service;

import com.csys.template.domain.DonationsHistory;

import com.csys.template.dto.DonationsHistoryDTO;
import com.csys.template.enumeration.StateEnum;
import com.csys.template.factory.DonationsHistoryFactory;
import com.csys.template.repository.PatientHistoriqueRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DonationHistoryService {

    private final PatientHistoriqueRepository patientHistoriqueRepository;

    public DonationHistoryService(PatientHistoriqueRepository patientHistoriqueRepository) {
        this.patientHistoriqueRepository = patientHistoriqueRepository;
    }

    @Transactional(readOnly = true)
    public List<DonationsHistoryDTO> findHistory(String code){
        List<DonationsHistory> list = new ArrayList<>();
        for (DonationsHistory patientHistorique : patientHistoriqueRepository.findAll()){
            com.csys.template.util.Preconditions.checkBusinessLogique(patientHistorique!=null,"history of this patient does not exist");
            if (patientHistorique.getCodePatient().equals(code)) {
                list.add(patientHistorique);
            }
        }
        return DonationsHistoryFactory.patientHistoriquesToPatientHistoriqueDTOS(list);
    }

    @Transactional
    public DonationsHistoryDTO addHistorique(DonationsHistoryDTO patientHistoriqueDTO){
        Preconditions.checkArgument (patientHistoriqueDTO != null, "Patient added!");
        DonationsHistory p = new DonationsHistory();
        p.setState(StateEnum.PENDING.intValue());
        DonationsHistory d = patientHistoriqueRepository.save(DonationsHistoryFactory.
                patientHisoriqueDTOToPatientHistorique(patientHistoriqueDTO));
        return DonationsHistoryFactory.patientHisoriqueToPatientHistoriqueDTO(d);
    }

    public DonationsHistory updateHistoriy(DonationsHistoryDTO historyDTO){
        DonationsHistory historyInDB = patientHistoriqueRepository.findByPatientCode(historyDTO.getCodePatient());
        Preconditions.checkArgument (historyInDB != null, "Patient has been updated");
        return patientHistoriqueRepository.save(DonationsHistoryFactory.patientHisoriqueDTOToPatientHistorique(historyDTO));
    }


}
