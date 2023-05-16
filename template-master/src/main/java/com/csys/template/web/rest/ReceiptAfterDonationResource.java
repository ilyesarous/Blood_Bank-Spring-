package com.csys.template.web.rest;

import com.csys.template.dto.ReceiptAfterDonationDTO;
import com.csys.template.dto.ReceiptBeforeDonationDTO;
import com.csys.template.service.ReceiptAfterDonationService;
import com.csys.template.util.Preconditions;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/receiptAfter")
public class ReceiptAfterDonationResource {



    private final ReceiptAfterDonationService receiptAfterDonationService;

    public ReceiptAfterDonationResource(ReceiptAfterDonationService receiptAfterDonationService) {
        this.receiptAfterDonationService = receiptAfterDonationService;
    }


    @GetMapping
    public List<ReceiptAfterDonationDTO> getAll(){

        List<ReceiptAfterDonationDTO> receiptAfterDonationDTOS = receiptAfterDonationService.findAll() ;

        return receiptAfterDonationDTOS;

    }

    @PostMapping
    public ResponseEntity<ReceiptAfterDonationDTO> addReceiptBefore(@RequestBody ReceiptAfterDonationDTO receiptAfterDonationDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(receiptAfterDonationDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", " You can not add patient with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        Preconditions.checkBusinessLogique(receiptAfterDonationDTO.getBlood()!=null,"error.couldn't-find-observation"+receiptAfterDonationDTO.getBlood());

        ReceiptAfterDonationDTO p = receiptAfterDonationService.addReceiptAfter(receiptAfterDonationDTO);
        return ResponseEntity.created(new URI("/patient"+ p.getCode())).body(p);
    }
}
