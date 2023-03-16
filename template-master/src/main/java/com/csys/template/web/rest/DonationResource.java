package com.csys.template.web.rest;

import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.DonationDTO;
import com.csys.template.service.DonationService;
import jakarta.validation.Valid;
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
@RequestMapping("/donation")
public class DonationResource {

    private final DonationService donationService;

    public DonationResource(DonationService donationService) {
        this.donationService = donationService;
    }


    @GetMapping
    public List<DonationDTO> getAll(){
        return donationService.findAll();
    }

    @PostMapping
    public ResponseEntity<DonationDTO> addDonation(@RequestBody  DonationDTO donationDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(donationDTO.getId() != null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", "You can not add a donation with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        DonationDTO d = donationService.addDonation(donationDTO);
        return ResponseEntity.created(new URI("/donation"+ d.getCode())).body(d);
    }

}
