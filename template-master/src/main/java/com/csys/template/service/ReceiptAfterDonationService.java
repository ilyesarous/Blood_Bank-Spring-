package com.csys.template.service;

import com.csys.template.domain.ReceiptAfterDonation;
import com.csys.template.dto.BloodDTO;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.ReceiptAfterDonationDTO;
import com.csys.template.dto.ReceiptBeforeDonationDTO;
import com.csys.template.factory.ReceiptAfterDonationFactory;
import com.csys.template.factory.ReceiptBeforeDonationFactory;
import com.csys.template.repository.ReceiptAfterDonationRepository;
import com.csys.template.repository.ReceiptBeforeDonationRepository;
import com.csys.template.util.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.csys.template.TemplateApplication.log;

@Service
@Transactional
public class ReceiptAfterDonationService {

    private final ReceiptAfterDonationRepository receiptAfterDonationRepository;
    private final CounterService counterService;
    private final BloodService bloodService;

    public ReceiptAfterDonationService(ReceiptBeforeDonationRepository receiptBeforeDonationRepository, ReceiptAfterDonationRepository receiptAfterDonationRepository, CounterService counterService, BloodService bloodService) {
        this.receiptAfterDonationRepository = receiptAfterDonationRepository;
        this.counterService = counterService;
        this.bloodService = bloodService;
    }


    @Transactional(readOnly = true)
    public List<ReceiptAfterDonationDTO> findAll() {
        log.debug("*** find All stock ***");
        List<ReceiptAfterDonation> receiptAfterDonations = receiptAfterDonationRepository.findAll();

        com.csys.template.util.Preconditions.checkBusinessLogique(receiptAfterDonations!=null,"error patient does not found");
        List<Integer> bloodCodes = receiptAfterDonations.stream()
                .map(ReceiptAfterDonation::getBlood)
                .distinct()
                .collect(Collectors.toList());
        List<BloodDTO> bloodDTOs = bloodService.getListBloodByCode(bloodCodes);
        List<ReceiptAfterDonationDTO> receiptAfterDonationDTOS = new ArrayList<>();
        receiptAfterDonations.forEach(p -> {
            ReceiptAfterDonationDTO receiptAfterDonationDTO = ReceiptAfterDonationFactory.ReceiptAfterdonationToReceiptAfterDonationDTO(p);
            Optional<BloodDTO> bloodDTOOptional = bloodDTOs.stream()
                    .filter(b -> b.getCodeBlood().compareTo(p.getBlood()) == 0)
                    .findFirst();
            if (bloodDTOOptional.isPresent()) {
                receiptAfterDonationDTO.setBlood(bloodDTOOptional.get().getBloodGrp()+bloodDTOOptional.get().getRhesus());
            }
            receiptAfterDonationDTOS.add(receiptAfterDonationDTO);
        });

        return receiptAfterDonationDTOS;
    }

    public ReceiptAfterDonationDTO addReceiptAfter(ReceiptAfterDonationDTO receiptAfterDonationDTO) {
        log.debug("*** add Donation ***");
        CounterDTO counter = counterService.findCounterByType("receiptAfter");
        receiptAfterDonationDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        String blood= receiptAfterDonationDTO.getBlood();
        Preconditions.checkBusinessLogique(receiptAfterDonationDTO.getBlood()!=null,"error.couldn't-find-observation"+blood);
        String bloodcode= bloodService.findBloodCodeByType(blood).toString();
        receiptAfterDonationDTO.setBlood(bloodcode);


        receiptAfterDonationRepository.save(ReceiptAfterDonationFactory.ReceiptbeforedonationDTOToReceiptbeforeDonation(receiptAfterDonationDTO));


        return receiptAfterDonationDTO;
    }

}
