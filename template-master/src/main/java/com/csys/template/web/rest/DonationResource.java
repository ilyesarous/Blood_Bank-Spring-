package com.csys.template.web.rest;

import com.csys.template.dto.BloodDTO;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.DonationDTO;
import com.csys.template.service.DonationService;
import com.csys.template.util.Preconditions;
import com.csys.template.util.RestPreconditions;
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
    @GetMapping("/{code}")
    public DonationDTO findOne(@PathVariable String code){
        DonationDTO donationDTO = donationService.findDonationByCode(code);
        Preconditions.checkBusinessLogique(donationDTO !=null, ENTITY_NAME + "donor does  Not found!");
        return donationDTO;
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
    @PutMapping("/{code}")
    public ResponseEntity<DonationDTO> updateBlood(@RequestBody @Valid DonationDTO donationDTO, @Valid @PathVariable String code)
            throws URISyntaxException {
        DonationDTO c = donationService.updateDonation(donationDTO);
        return ResponseEntity.created(new URI("/donation" + c.getCode())).body(c);
    }

}
