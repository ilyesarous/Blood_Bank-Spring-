package com.csys.template.service;

import com.csys.template.domain.ReceiptBeforeDonation;
import com.csys.template.domain.Stock;
import com.csys.template.dto.BloodDTO;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.ReceiptBeforeDonationDTO;
import com.csys.template.dto.StockDTO;
import com.csys.template.factory.ReceiptBeforeDonationFactory;
import com.csys.template.factory.StockFactory;
import com.csys.template.repository.ReceiptBeforeDonationRepository;
import com.csys.template.util.Preconditions;
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
public class ReceiptBeforeDonationService {

    private final ReceiptBeforeDonationRepository receiptBeforeDonationRepository;
    private final CounterService counterService;
    private final BloodService bloodService;

    public ReceiptBeforeDonationService(ReceiptBeforeDonationRepository receiptBeforeDonationRepository, CounterService counterService, BloodService bloodService) {
        this.receiptBeforeDonationRepository = receiptBeforeDonationRepository;
        this.counterService = counterService;
        this.bloodService = bloodService;
    }

//    @Transactional(readOnly = true)
//    public List<ReceiptBeforeDonationDTO> findAll() {
//        log.debug("*** find All stock ***");
//        List<ReceiptBeforeDonation> receiptBeforeDonations = receiptBeforeDonationRepository.findAll();
//        Integer taille= receiptBeforeDonations.size();
//        com.csys.template.util.Preconditions.checkBusinessLogique(receiptBeforeDonations!=null,"error patient does not found");
//        List<Integer> bloodCodes = receiptBeforeDonations.stream()
//                .map(ReceiptBeforeDonation::getBlood)
//                .distinct()
//                .collect(Collectors.toList());
//        List<BloodDTO> bloodDTOs = bloodService.getListBloodByCode(bloodCodes);
//        List<ReceiptBeforeDonationDTO> receiptBeforeDonationDTOS = new ArrayList<>();
//        receiptBeforeDonations.forEach(p -> {
//            ReceiptBeforeDonationDTO receiptBeforeDonationDTO = ReceiptBeforeDonationFactory.ReceiptbeforedonationToReceiptbeforeDonationDTO(p);
//            Optional<BloodDTO> bloodDTOOptional = bloodDTOs.stream()
//                    .filter(b -> b.getCodeBlood().compareTo(p.getBlood()) == 0)
//                    .findFirst();
//            if (bloodDTOOptional.isPresent()) {
//                receiptBeforeDonationDTO.setBlood(bloodDTOOptional.get().getBloodGrp()+bloodDTOOptional.get().getRhesus());
//            }
//            receiptBeforeDonationDTOS.add(receiptBeforeDonationDTO);
//        });
//
//        return receiptBeforeDonationDTOS;
//    }
    @Transactional(readOnly = true)
    public ReceiptBeforeDonationDTO findOne() {
        log.debug("*** find All stock ***");
        List<ReceiptBeforeDonation> receiptBeforeDonations = receiptBeforeDonationRepository.findAll();

        com.csys.template.util.Preconditions.checkBusinessLogique(receiptBeforeDonations!=null,"error patient does not found");
        List<Integer> bloodCodes = receiptBeforeDonations.stream()
                .map(ReceiptBeforeDonation::getBlood)
                .distinct()
                .collect(Collectors.toList());
        List<BloodDTO> bloodDTOs = bloodService.getListBloodByCode(bloodCodes);
        List<ReceiptBeforeDonationDTO> receiptBeforeDonationDTOS = new ArrayList<>();
        receiptBeforeDonations.forEach(p -> {
            ReceiptBeforeDonationDTO receiptBeforeDonationDTO = ReceiptBeforeDonationFactory.ReceiptbeforedonationToReceiptbeforeDonationDTO(p);
            Optional<BloodDTO> bloodDTOOptional = bloodDTOs.stream()
                    .filter(b -> b.getCodeBlood().compareTo(p.getBlood()) == 0)
                    .findFirst();
            if (bloodDTOOptional.isPresent()) {
                receiptBeforeDonationDTO.setBlood(bloodDTOOptional.get().getBloodGrp()+bloodDTOOptional.get().getRhesus());
            }
            receiptBeforeDonationDTOS.add(receiptBeforeDonationDTO);

        });
        ReceiptBeforeDonationDTO receiptBeforeDonation2 = new ReceiptBeforeDonationDTO();
        Integer taille= receiptBeforeDonationDTOS.size();
        for (int i=0;i<taille;i++)
        {
            if (i==(taille-1))
            {
                 receiptBeforeDonation2 = receiptBeforeDonationDTOS.get(i);
            }
        }

        return receiptBeforeDonation2 ;
    }

    public ReceiptBeforeDonationDTO addReceipt(ReceiptBeforeDonationDTO receiptBeforeDonationDTO) {
        log.debug("*** add Donation ***");
        CounterDTO counter = counterService.findCounterByType("receiptBefore");
        receiptBeforeDonationDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        String blood= receiptBeforeDonationDTO.getBlood();
        Preconditions.checkBusinessLogique(receiptBeforeDonationDTO.getBlood()!=null,"error.couldn't-find-observation"+blood);
        String bloodcode= bloodService.findBloodCodeByType(blood).toString();
        receiptBeforeDonationDTO.setBlood(bloodcode);


        receiptBeforeDonationRepository.save(ReceiptBeforeDonationFactory.ReceiptbeforedonationDTOToReceiptbeforeDonation(receiptBeforeDonationDTO));


        return receiptBeforeDonationDTO;
    }
}
