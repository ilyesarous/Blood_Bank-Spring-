package com.csys.template.web.rest;

import com.csys.template.domain.Stock;
import com.csys.template.dto.PatientDTO;
import com.csys.template.dto.ReceiptBeforeDonationDTO;
import com.csys.template.dto.StockDTO;
import com.csys.template.search.StockSearch;
import com.csys.template.service.ReceiptBeforeDonationService;
import com.csys.template.util.Preconditions;
import org.springframework.data.jpa.domain.Specification;
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
@RequestMapping("/receiptBefore")
public class ReceiptBeforeDonationResource {

    private final ReceiptBeforeDonationService receiptBeforeDonationService;

    public ReceiptBeforeDonationResource(ReceiptBeforeDonationService receiptBeforeDonationService) {
        this.receiptBeforeDonationService = receiptBeforeDonationService;
    }

    @GetMapping
    public List<ReceiptBeforeDonationDTO> getAll(){

        List<ReceiptBeforeDonationDTO> receiptBeforeDonationDTOS = receiptBeforeDonationService.findAll() ;

        return receiptBeforeDonationDTOS;

    }

    @PostMapping
    public ResponseEntity<ReceiptBeforeDonationDTO> addReceiptBefore(@RequestBody ReceiptBeforeDonationDTO receiptBeforeDonationDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(receiptBeforeDonationDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", " You can not add patient with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        Preconditions.checkBusinessLogique(receiptBeforeDonationDTO.getBlood()!=null,"error.couldn't-find-observation"+receiptBeforeDonationDTO.getBlood());

        ReceiptBeforeDonationDTO p = receiptBeforeDonationService.addReceipt(receiptBeforeDonationDTO);
        return ResponseEntity.created(new URI("/patient"+ p.getCode())).body(p);
    }
}
