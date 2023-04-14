package com.csys.template.service;

import com.csys.template.domain.Demande;
import com.csys.template.dto.*;
import com.csys.template.factory.DemandeFactory;
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
    private final StockService stockService;

    public DemandeService(DemandeRepository demandeRepository, ParamServiceClient paramServiceClient, ParamMedecinService paramMedecinService, CounterService counterService, DemandeHistoryService demandeHistoryService, BloodService bloodService, StockService stockService) {
        this.demandeRepository = demandeRepository;
        this.paramServiceClient = paramServiceClient;
        this.paramMedecinService = paramMedecinService;
        this.counterService = counterService;
        this.demandeHistoryService = demandeHistoryService;
        this.bloodService = bloodService;
        this.stockService = stockService;
    }

    @Transactional(readOnly = true)
    public List<DemandeDTO> findAll() {


        List<Demande> demande = demandeRepository.findAll();
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
                demandeDTO1.setBlood(bloodDTOOptional.get().getBloodGrp() + bloodDTOOptional.get().getRhesus());
            }
            demandeDTOS.add(demandeDTO1);
        });
        return demandeDTOS;
    }

    @Transactional(readOnly = true)
    public DemandeDTO findStockByCode(String code) {
        Demande demande = demandeRepository.findByCode(code);
        Preconditions.checkArgument(demande != null, "Demande not found ");
        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);

        return demandeDTO;
    }

    @Transactional(readOnly = true)
    public DemandeDTO findDemandeByCodeMed(String code) {
        Demande demande = demandeRepository.findDemandeByCodeMedecin(code);
        Preconditions.checkArgument(demande != null, "Demande doctor not found !");
        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);

        return demandeDTO;
    }

    @Transactional
    public DemandeDTO addDemande(DemandeDTO demandeDTO) {
        Preconditions.checkArgument(demandeDTO != null, "Demande added!");

        CounterDTO counter = counterService.findCounterByType("demande");
        demandeDTO.setCode(counter.getPrefix() + counter.getSuffix());
        counter.setSuffix(counter.getSuffix() + 1);
        counterService.updateCounter(counter);

        Integer codeMed = (Integer.parseInt(demandeDTO.getCodeMedecin()));
//        MedecinDTO medecinDTO = paramMedecinService.serviceFindOne(codeMed);
        Integer code = (Integer.parseInt(demandeDTO.getCodeService()));
//        ServiceDTO serviceDTO = paramServiceClient.serviceFindOne(code);
        demandeDTO.setCreateDate(LocalDate.now().toString());
        String ch = demandeDTO.getBlood();
        String x = bloodService.findBloodCodeByType(ch).toString();
        demandeDTO.setBlood(x);
//        String name = paramMedecinService.serviceFindNameByCode(Integer.parseInt(demandeDTO.getCodeMedecin()));
//        demandeDTO.setNameMedecin(name);
//        demandeDTO.setNameService(paramServiceClient.serviceFindNameByCode(Integer.parseInt(demandeDTO.getCodeService())));

        Demande d = demandeRepository.save(DemandeFactory.demandeDTOToDemande(demandeDTO));
        DemandeHistoryDTO demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
        demandeHistoryDTO.setCreateDate(demandeDTO.getCreateDate());
        demandeHistoryService.addDemandeHistory(demandeHistoryDTO);
        return DemandeFactory.demandeToDemandeDTO(d);

    }

    @Transactional
    public Demande updateDemande(DemandeDTO demandeDTO) {
        Demande demande = demandeRepository.findByCode(demandeDTO.getCode());
        Integer blood = demande.getBlood();
        String x= bloodService.findTypeByBloodCode(demande.getBlood());
        Preconditions.checkArgument(demande != null, "demande does not exist!");
        DemandeHistoryDTO demandeHistoryDTO = new DemandeHistoryDTO();
        Demande demande1 = new Demande();
        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setBlood(x);
        demandeDTO.setUsercreate(demande.getUsercreate());

        Integer qt = stockService.getQantiteTotal(demandeDTO.getBlood());
        Preconditions.checkArgument(qt != 0, "Blood not existe in storage!"+ demandeDTO.getBlood() );
        Integer QD = Integer.parseInt(demandeDTO.getQuantiter());



        List<StockDTO> stockDTOS = stockService.findStockByblood(demandeDTO.getBlood());
        Preconditions.checkArgument(stockDTOS != null, "Blood .............!" );


        if (qt >= QD) {
            for (Integer i = 0; i < QD; i++) {

                stockService.remove(stockDTOS.get(i).getCode());
            }
            demandeDTO.setStatus("SOLVED");
            demandeDTO.setQuantiter("0");
            demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
            DemandeDTO demandeDTO1 = remove(demandeDTO.getCode());



        }
        else {
            for (Integer i = 0; i < qt; i++) {
                Preconditions.checkArgument(stockDTOS.get(i).getCode() != null, "Blood .............!"+stockDTOS.get(i).getCode() );
                stockService.remove(stockDTOS.get(i).getCode());


            }

            Integer quntite = QD - qt;
            String ch = quntite.toString();
            demandeDTO.setQuantiter(ch);
            demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
            demandeDTO.setBlood(blood.toString());
            demande1 = DemandeFactory.demandeDTOToDemande(demandeDTO);
            demandeHistoryService.addDemandeHistory(demandeHistoryDTO);
            demandeRepository.save(demande1);
        }



        return demande1;
    }

    @Transactional
    public DemandeDTO remove(String code) {
        Demande demande = demandeRepository.findByCode(code);
        Preconditions.checkArgument(demande != null, "Demande remove!"+code);
        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);
        demandeRepository.deleteById(demande.getCode());
        return demandeDTO;
    }

    @Transactional
    public DemandeDTO removeByCodeMed(String code) {
        Demande demande = demandeRepository.findDemandeByCodeMedecin(code);
        Preconditions.checkArgument(demande != null, "Demande medecin remove!"+code);
        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);
        demandeRepository.deleteById(demande.getCode());
        return demandeDTO;
    }
}
