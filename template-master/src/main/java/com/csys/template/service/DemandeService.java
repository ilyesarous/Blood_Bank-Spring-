package com.csys.template.service;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Demande;
import com.csys.template.domain.Patient;
import com.csys.template.dto.*;
import com.csys.template.factory.DemandeFactory;
import com.csys.template.factory.PatientFactory;
import com.csys.template.repository.DemandeRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemandeService {

    private final DemandeRepository demandeRepository;
    private final ParamServiceClient paramServiceClient;
    private final ParamMedecinService paramMedecinService;
    private final CounterService counterService;
    private final DemandeHistoryService demandeHistoryService;
    private final BloodService bloodService;

    public DemandeService(DemandeRepository demandeRepository, ParamServiceClient paramServiceClient, ParamMedecinService paramMedecinService, CounterService counterService, DemandeHistoryService demandeHistoryService, BloodService bloodService) {
        this.demandeRepository = demandeRepository;
        this.paramServiceClient = paramServiceClient;
        this.paramMedecinService = paramMedecinService;
        this.counterService = counterService;
        this.demandeHistoryService = demandeHistoryService;
        this.bloodService = bloodService;
    }

    @Transactional(readOnly = true)
    public List<DemandeDTO> findAll(){


      List <Demande> demande= demandeRepository.findAll();
        List<Integer> bloodCodes = demande.stream()
                .map(Demande::getBlood)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(bloodCodes);
        List<BloodDTO> bloodDTOs = bloodService.getListBloodByCode(bloodCodes);
        List<DemandeDTO> demandeDTOS = new ArrayList<>();
        demande.forEach(p -> {
            DemandeDTO demandeDTO1 = DemandeFactory.demandeToDemandeDTO(p);
            Optional<BloodDTO> bloodDTOOptional = bloodDTOs.stream()
                    .filter(b -> b.getCodeBlood().compareTo(p.getBlood()) == 0)
                    .findFirst();
            if (bloodDTOOptional.isPresent()) {
                demandeDTO1.setBlood(bloodDTOOptional.get().getBloodGrp()+bloodDTOOptional.get().getRhesus());
            }
            demandeDTOS.add(demandeDTO1);
        });
        return demandeDTOS;
    }

    @Transactional(readOnly = true)
    public DemandeDTO findStockByCode(String code) {
        Demande demande = demandeRepository.findByCode(code);

        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);

        return demandeDTO;
    }
    @Transactional
    public DemandeDTO addDemande(DemandeDTO demandeDTO){
        Preconditions.checkArgument (demandeDTO != null, "Demande added!");

        CounterDTO counter = counterService.findCounterByType("demande");
        demandeDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);

        Integer codeMed = (Integer.parseInt(demandeDTO.getCodeMedecin()));
        MedecinDTO medecinDTO = paramMedecinService.serviceFindOne(codeMed);
        Integer code = (Integer.parseInt(demandeDTO.getCodeService()));
        ServiceDTO serviceDTO = paramServiceClient.serviceFindOne(code);
        demandeDTO.setCreateDate(LocalDate.now().toString());
        String ch =demandeDTO.getBlood();
        String x=bloodService.findBloodCodeByType(ch).toString();
        demandeDTO.setBlood(x);

        Demande d = demandeRepository.save(DemandeFactory.demandeDTOToDemande(demandeDTO));
        DemandeHistoryDTO demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
        demandeHistoryDTO.setCreateDate(demandeDTO.getCreateDate());
        demandeHistoryService.addDemandeHistory(demandeHistoryDTO);
        return DemandeFactory.demandeToDemandeDTO(d);

    }
    @Transactional
    public Demande updateDemande(DemandeDTO demandeDTO){
        Demande demande = demandeRepository.findByCode(demandeDTO.getCode());


        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        String blood=bloodService.findTypeByBloodCode(demande.getBlood());
        demandeDTO.setBlood(blood);
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
    public DemandeDTO remove(String code){
        Demande demande = demandeRepository.findByCode(code);
        Preconditions.checkArgument (demande != null, "Demande remove!");
        DemandeDTO demandeDTO =DemandeFactory.demandeToDemandeDTO(demande);
        demandeRepository.deleteById(demande.getCode());
        return demandeDTO;
    }
}
