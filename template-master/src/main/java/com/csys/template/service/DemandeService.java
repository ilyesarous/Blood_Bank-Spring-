package com.csys.template.service;

import com.csys.template.domain.Demande;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.DemandeDTO;
import com.csys.template.factory.DemandeFactory;
import com.csys.template.repository.DemandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DemandeService {

    private final DemandeRepository demandeRepository;
    private final ParamServiceClient paramServiceClient;
    private final ParamMedecinService paramMedecinService;
    private final CounterService counterService;

    public DemandeService(DemandeRepository demandeRepository, ParamServiceClient paramServiceClient, ParamMedecinService paramMedecinService, CounterService counterService) {
        this.demandeRepository = demandeRepository;
        this.paramServiceClient = paramServiceClient;
        this.paramMedecinService = paramMedecinService;
        this.counterService = counterService;
    }

    @Transactional(readOnly = true)
    public List<DemandeDTO> findAll(){
        return DemandeFactory.stocksToStocksDTO(demandeRepository.findAll());
    }
    @Transactional(readOnly = true)
    public DemandeDTO findStockByCode(String code) {
        Demande demande = demandeRepository.findByCode(code);

        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);

        return demandeDTO;
    }
    @Transactional
    public DemandeDTO addDemande(DemandeDTO demandeDTO){
        CounterDTO counter = counterService.findCounterByType("demande");
        demandeDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        Integer codeMed=(Integer.parseInt(demandeDTO.getCodeMedecin()));
//        MedecinDTO medecinDTO=paramMedecinService.serviceFindOne(codeMed);
        Integer code= (Integer.parseInt(demandeDTO.getCodeService()));
//        ServiceDTO serviceDTO= paramServiceClient.serviceFindOne(code);

        Demande d = demandeRepository.save(DemandeFactory.demandeDTOToDemande(demandeDTO));
        return DemandeFactory.demandeToDemandeDTO(d);

    }
    @Transactional
    public Demande updateDemande(DemandeDTO demandeDTO){
        Demande demande = demandeRepository.findByCode(demandeDTO.getCode());


        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setBlood(demande.getBlood());
        demandeDTO.setUsercreate(demande.getUsercreate());

        return demandeRepository.save(DemandeFactory.demandeDTOToDemande(demandeDTO));
    }
    @Transactional
    public DemandeDTO remove(String code){
        Demande demande = demandeRepository.findByCode(code);
        DemandeDTO demandeDTO =DemandeFactory.demandeToDemandeDTO(demande);
        demandeRepository.deleteById(demande.getCode());
        return demandeDTO;
    }
}
