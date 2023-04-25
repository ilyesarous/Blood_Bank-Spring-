package com.csys.template.service;

import com.csys.template.domain.Donation;
import com.csys.template.domain.DonationsHistory;

import com.csys.template.dto.DonationDTO;
import com.csys.template.dto.DonationsHistoryDTO;
import com.csys.template.enumeration.StateEnum;
import com.csys.template.factory.DonationFactory;
import com.csys.template.factory.DonationsHistoryFactory;
import com.csys.template.repository.PatientHistoriqueRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public List<DonationsHistoryDTO> findAll() {
        List<DonationsHistory> donations = patientHistoriqueRepository.findAll();
        List<DonationsHistoryDTO> donationDTOS = DonationsHistoryFactory.patientHistoriquesToPatientHistoriqueDTOS(donations);

        return donationDTOS;
    }
//    @Transactional(readOnly = true)
//    public List<DonationsHistoryDTO> findById(Integer id) {
//        List<DonationsHistory> donation = patientHistoriqueRepository.findById(id);
//        List<DonationsHistoryDTO> donationDTO = DonationsHistoryFactory.patientHistoriquesToPatientHistoriqueDTOS(donation);
//
//        return donationDTO;
//    }
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
    public DonationsHistoryDTO addHistorique(DonationsHistoryDTO HistoriqueDTO){
        com.csys.template.util.Preconditions.checkBusinessLogique(HistoriqueDTO != null, "error.couldn't-find-donateur");
        DonationsHistory p = new DonationsHistory();
//        p.setState(StateEnum.PENDING.intValue());
        DonationsHistory d = patientHistoriqueRepository.save(DonationsHistoryFactory.
                patientHisoriqueDTOToPatientHistorique(HistoriqueDTO));
        return DonationsHistoryFactory.patientHisoriqueToPatientHistoriqueDTO(d);
    }

    public DonationsHistoryDTO updateHistoriy(DonationsHistoryDTO historyDTO){

        DonationsHistory historyInDB = patientHistoriqueRepository.findByCode(historyDTO.getCode());
        com.csys.template.util.Preconditions.checkBusinessLogique(historyInDB != null, "error.couldn't-find-donateur");
        DonationsHistory p = new DonationsHistory();
        p= patientHistoriqueRepository.save(DonationsHistoryFactory.patientHisoriqueDTOToPatientHistorique(historyDTO));
        return DonationsHistoryFactory.patientHisoriqueToPatientHistoriqueDTO(p);
    }


}
