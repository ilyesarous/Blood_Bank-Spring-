package com.csys.template.web.rest;

import com.csys.template.domain.Demande;
import com.csys.template.dto.DemandeDTO;
import com.csys.template.service.DemandeService;
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
@RequestMapping("/demandeeee")
public class DemandeResource {
    private final DemandeService demandeService;

    public DemandeResource(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @GetMapping
    public List<DemandeDTO> getAll(){

        return demandeService.findAll();
    }
    @GetMapping("codeMed/{code}")
    public List<DemandeDTO> getWithCodeMed(@PathVariable String code){
        List<DemandeDTO> demandeDTO = demandeService.findByCodeMed(code);
        return demandeDTO;
    }
    @PostMapping
    public ResponseEntity<DemandeDTO> addCounter(@RequestBody @Valid DemandeDTO demandeDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(demandeDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", "You can not add a counter with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        DemandeDTO c = demandeService.addDemande(demandeDTO);
        return ResponseEntity.created(new URI("/demande"+ c.getCode())).body(c);
    }

    @PutMapping("/{code}")
    public ResponseEntity<DemandeDTO> updateDemande(@RequestBody @Valid DemandeDTO donationDTO, @Valid @PathVariable String code)
            throws URISyntaxException {
        DemandeDTO c = demandeService.updateDemande(donationDTO);
        return ResponseEntity.created(new URI("/donation" + c.getCode())).body(c);
    }

    @PutMapping("/rej/{codeMed}")
    public ResponseEntity<DemandeDTO> updateDemandeRejected(@RequestBody @Valid DemandeDTO donationDTO, @Valid @PathVariable String codeMed)
            throws URISyntaxException {
        DemandeDTO c = demandeService.updateDemandeToRejected(donationDTO);
        return ResponseEntity.created(new URI("/donation" + c.getCode())).body(c);
    }

//    @DeleteMapping("/{codeMed}")
//    public DemandeDTO  DeleteDemande(@RequestBody @Valid DemandeDTO donationDTO, @Valid @PathVariable String codeMed)
//            throws URISyntaxException {
//        DemandeDTO c = demandeService.remove(codeMed);
//        return  c;
//    }
}
