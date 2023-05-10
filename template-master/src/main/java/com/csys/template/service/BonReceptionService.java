package com.csys.template.service;

import com.csys.template.domain.BonReception;
import com.csys.template.domain.Stock;
import com.csys.template.dto.*;
import com.csys.template.factory.BonReceptionFactory;
import com.csys.template.factory.StockFactory;
import com.csys.template.repository.BonReceptionRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.csys.template.TemplateApplication.log;

@Service
@Transactional
public class BonReceptionService {
    private final CounterService counterService;
    private final BloodService bloodService;
    private final BonReceptionRepository bonReceptionRepository;

    public BonReceptionService(CounterService counterService, BloodService bloodService, BonReceptionRepository bonReceptionRepository) {
        this.counterService = counterService;
        this.bloodService = bloodService;
        this.bonReceptionRepository = bonReceptionRepository;
    }

    @Transactional(readOnly = true)
    public List<BonReceptionDTO> findAll() {
        log.debug("*** find All stock ***");
        List<BonReception> bonReceptions = bonReceptionRepository.findAll();
        List<BonReception> bonReception=new ArrayList<>();

        com.csys.template.util.Preconditions.checkBusinessLogique(bonReceptions!=null,"error patient does not found");
        List<Integer> bloodCodes = bonReceptions.stream()
                .map(BonReception:: getBloodCode)
                .distinct()
                .collect(Collectors.toList());
        List<BloodDTO> bloodDTOs = bloodService.getListBloodByCode(bloodCodes);
        List<BonReceptionDTO> bonReceptionDTOS = new ArrayList<>();
        bonReceptions.forEach(p -> {
            BonReceptionDTO bonReceptionDTO = BonReceptionFactory.bonReceptionToBonReceptionDTO(p);
            Optional<BloodDTO> bloodDTOOptional = bloodDTOs.stream()
                    .filter(b -> b.getCodeBlood().compareTo(p.getBloodCode()) == 0)
                    .findFirst();
            if (bloodDTOOptional.isPresent()) {
                bonReceptionDTO.setBloodCode(bloodDTOOptional.get().getBloodGrp()+bloodDTOOptional.get().getRhesus());
            }
            bonReceptionDTOS.add(bonReceptionDTO);
        });

        return bonReceptionDTOS;
    }

    @Transactional(readOnly = true)
    public List<BonReceptionDTO> findByCode(String code) {
        log.debug("*** find Bon Reception By Code ***");
        List<BonReception> bonReceptions = bonReceptionRepository.findByCode(code);
        com.csys.template.util.Preconditions.checkBusinessLogique(bonReceptions == null,"Bon Reception  Not found!"+bonReceptions);
        List<BonReceptionDTO> bonReceptionDTOS = BonReceptionFactory.bonReceptionSToBonReceptionsDTO(bonReceptions);

        return bonReceptionDTOS;
    }


    @Transactional
    public BonReceptionDTO addBonReception(BonReceptionDTO bonReceptionDTO){
        log.debug("*** add Stock ***");

        CounterDTO counter = counterService.findCounterByType("BonReception");
        bonReceptionDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);


        BonReception d = bonReceptionRepository.save(BonReceptionFactory.BonReceptionDTOToBonReception(bonReceptionDTO));
        return BonReceptionFactory.bonReceptionToBonReceptionDTO(d);
    }

}
