package com.csys.template.service;

import com.csys.template.domain.Demande;
import com.csys.template.dto.*;
import com.csys.template.factory.DemandeFactory;
import com.csys.template.repository.DemandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DemandeService {

    private final DemandeRepository demandeRepository;
    private final ParamServiceClient paramServiceClient;
    private final ParamMedecinService paramMedecinService;
    private final CounterService counterService;
    private final DemandeHistoryService demandeHistoryService;

    public DemandeService(DemandeRepository demandeRepository, ParamServiceClient paramServiceClient, ParamMedecinService paramMedecinService, CounterService counterService, DemandeHistoryService demandeHistoryService) {
        this.demandeRepository = demandeRepository;
        this.paramServiceClient = paramServiceClient;
        this.paramMedecinService = paramMedecinService;
        this.counterService = counterService;
        this.demandeHistoryService = demandeHistoryService;
    }

    @Transactional(readOnly = true)
    public List<DemandeDTO> findAll() {
        return DemandeFactory.stocksToStocksDTO(demandeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public DemandeDTO findStockByCode(String code) {
        Demande demande = demandeRepository.findByCode(code);

        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);

        return demandeDTO;
    }

    @Transactional
    public DemandeDTO addDemande(DemandeDTO demandeDTO) {
        CounterDTO counter = counterService.findCounterByType("demande");
        demandeDTO.setCode(counter.getPrefix() + counter.getSuffix());
        counter.setSuffix(counter.getSuffix() + 1);
        counterService.updateCounter(counter);
        Integer codeMed = (Integer.parseInt(demandeDTO.getCodeMedecin()));
        MedecinDTO medecinDTO = paramMedecinService.serviceFindOne(codeMed);
        Integer code = (Integer.parseInt(demandeDTO.getCodeService()));
        ServiceDTO serviceDTO = paramServiceClient.serviceFindOne(code);
        demandeDTO.setCreateDate(LocalDate.now().toString());
        Demande d = demandeRepository.save(DemandeFactory.demandeDTOToDemande(demandeDTO));
        DemandeHistoryDTO demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
        demandeHistoryDTO.setCreateDate(demandeDTO.getCreateDate());
        demandeHistoryService.addDemandeHistory(demandeHistoryDTO);
        return DemandeFactory.demandeToDemandeDTO(d);

    }

    @Transactional
    public Demande updateDemande(DemandeDTO demandeDTO) {
        Demande demande = demandeRepository.findByCode(demandeDTO.getCode());


        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setBlood(demande.getBlood());
        demandeDTO.setUsercreate(demande.getUsercreate());

        Demande demande1 = DemandeFactory.demandeDTOToDemande(demandeDTO);
        if (demandeDTO.getStatus().equals("SOLVED") || demandeDTO.getStatus().equals("REJECTED")) {

            DemandeHistoryDTO demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
            demandeHistoryService.addDemandeHistory(demandeHistoryDTO);
            DemandeDTO demandeDTO1 = remove(demandeDTO.getCode());
        } else {
            demandeRepository.save(demande1);
        }
        return demande1;
    }

    @Transactional
    public DemandeDTO remove(String code) {
        Demande demande = demandeRepository.findByCode(code);
        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);
        demandeRepository.deleteById(demande.getCode());
        return demandeDTO;
    }
}
