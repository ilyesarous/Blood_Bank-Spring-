package com.csys.template.service;

import com.csys.template.domain.Demande;
import com.csys.template.domain.Stock;
import com.csys.template.dto.*;
import com.csys.template.factory.DemandeFactory;
import com.csys.template.repository.DemandeRepository;
import com.google.common.base.Preconditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.websocket.Session;
import com.querydsl.jpa.hibernate.HibernateUtil;
//import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.csys.template.TemplateApplication.log;

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
        log.debug("***find All***");

        List<Demande> demandes=demandeRepository.findByOrderByState();
        List<Demande> demande= new ArrayList<>();
        for (Demande d: demandes
             ) {
            if (d.getStatus().equals(3))
            {
                demande.add(d);
            }

        }

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
        log.debug("***find Stock By Code***");
        Demande demande = demandeRepository.findByCode(code);
        com.csys.template.util.Preconditions.checkBusinessLogique(demande != null, "error.couldn't-find-stock");
        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);

        return demandeDTO;
    }

    @Transactional(readOnly = true)
    public List<DemandeDTO> findByCodeMed(String codeMed) {
        log.debug("***find By CodeMed***");
       List <Demande> demande = demandeRepository.findBycodeMedecin(codeMed);
        com.csys.template.util.Preconditions.checkBusinessLogique(demande != null, "error.couldn't-find-stock");
        List<DemandeDTO> demandeDTO = DemandeFactory.demandesToDemandeDTO(demande);

        return demandeDTO;
    }

    @Transactional
    public DemandeDTO addDemande(DemandeDTO demandeDTO) {
        log.debug("***add Demande***");
        com.csys.template.util.Preconditions.checkBusinessLogique(demandeDTO != null, "demande add ! ");

        CounterDTO counter = counterService.findCounterByType("demande");
        demandeDTO.setCode(counter.getPrefix() + counter.getSuffix());
        counter.setSuffix(counter.getSuffix() + 1);
        counterService.updateCounter(counter);

        Integer codeMed = demandeDTO.getCodeMedecin();
//        MedecinDTO medecinDTO = paramMedecinService.serviceFindOne(codeMed);
        Integer code = demandeDTO.getCodeService();
//        ServiceDTO serviceDTO = paramServiceClient.serviceFindOne(code);
        demandeDTO.setCreateDate(LocalDate.now().toString());
        demandeDTO.setCreateDateLd(LocalDate.now());
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
    public DemandeDTO updateDemande(DemandeDTO demandeDTO) {

        log.debug("***update Demande***");

        com.csys.template.util.Preconditions.checkBusinessLogique(!demandeDTO.getStatus().equals("REJECTED") , "error.couldn't-find-demande");
        Demande demande = demandeRepository.findByCode(demandeDTO.getCode());
        String x= bloodService.findTypeByBloodCode(demande.getBlood());

        Integer blood = demande.getBlood();
        com.csys.template.util.Preconditions.checkBusinessLogique(demande != null, "error.couldn't-find-demande");
        DemandeHistoryDTO demandeHistoryDTO = new DemandeHistoryDTO();
        Demande demande1 = new Demande();
        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setBlood(x);
        demandeDTO.setUsercreate(demande.getUsercreate());
         Integer bl =bloodService.findBloodCodeByType(demandeDTO.getBlood());

        Integer qt = stockService.getQantiteTotal(bl);

        Integer QD = Integer.parseInt(demandeDTO.getQuantiter());


        List<StockDTO> stockDTOS = stockService.findByblood(bl);

        EntityManager entityManager = null;

        if (qt >= QD) {
            for (int i = 0; i < QD; i++) {

//                Stock stock = entityManager.find(Stock.class, stockDTOS.get(i).getId());
//                entityManager.lock(stock, LockModeType.OPTIMISTIC);
//                Query query = entityManager.createQuery("from Student where id = :id");
//                query.setParameter("id", stockDTOS.get(i).getId());
//                query.setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT);
//                query.getResultList();
                stockService.update(stockDTOS.get(i));
            }
            demandeDTO.setStatus("SOLVED");
            demandeDTO.setQuantiter("0");
            demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
            DemandeDTO demandeDTO1 = remove(demandeDTO.getCode());



        }
        else {
            for (int i = 0; i < qt; i++) {
                Preconditions.checkArgument(stockDTOS.get(i).getCode() != null, "Blood .............!"+stockDTOS.get(i).getCode() );
                stockService.update(stockDTOS.get(i));


            }

            int quntite = QD - qt;
            String ch = Integer.toString(quntite);
            demandeDTO.setQuantiter(ch);
            demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
            demandeDTO.setBlood(blood.toString());
            demande1 = DemandeFactory.demandeDTOToDemande(demandeDTO);
            demandeHistoryService.addDemandeHistory(demandeHistoryDTO);
            demandeRepository.save(demande1);
        }



        return demandeDTO;
    }

    @Transactional
    public DemandeDTO updateDemandeToRejected(DemandeDTO demandeDTO) {
        log.debug("***update Demande To Rejected***");
        Demande demande = demandeRepository.findByCode(demandeDTO.getCode());
        String a=demande.getBlood().toString();
        com.csys.template.util.Preconditions.checkBusinessLogique(demande != null, "error.couldn't-find-demande");
        demandeDTO.setCode(demande.getCode());
        demandeDTO.setCodeMedecin(demande.getCodeMedecin());
        demandeDTO.setNameMedecin(demande.getNameMedecin());
        demandeDTO.setCodeService(demande.getCodeService());
        demandeDTO.setNameService(demande.getNameService());
        demandeDTO.setBlood(a);
        demandeDTO.setUsercreate(demande.getUsercreate());
        demandeDTO.setStatus("REJECTED");

        DemandeHistoryDTO demandeHistoryDTO = DemandeFactory.demandeToDemandeHistoryDTO(demandeDTO);
        demandeHistoryService.addDemandeHistory(demandeHistoryDTO);
        demandeRepository.save(DemandeFactory.demandeDTOToDemande(demandeDTO));
//        DemandeDTO demandeDTO2 = remove(demandeDTO.getCode());
        return demandeDTO;

    }

    @Transactional
    public DemandeDTO remove(String code) {
        log.debug("***remove demande ***");
        Demande demande = demandeRepository.findByCode(code);
        com.csys.template.util.Preconditions.checkBusinessLogique(demande != null, "error.couldn't-find-demande");
        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);
        demandeRepository.deleteById(demande.getCode());
        return demandeDTO;
    }

//    @Transactional
//    public DemandeDTO removeByCodeMed(String code) {
//          log.debug("***remove By CodeMed ***");
//        Demande demande = demandeRepository.findDemandeByCodeMedecin(code);
//        Preconditions.checkArgument(demande != null, "Demande medecin remove!"+code);
//        DemandeDTO demandeDTO = DemandeFactory.demandeToDemandeDTO(demande);
//        demandeRepository.deleteById(demande.getCode());
//        return demandeDTO;
//    }
}
