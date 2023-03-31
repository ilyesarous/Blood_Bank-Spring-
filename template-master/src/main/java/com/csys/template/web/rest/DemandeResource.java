package com.csys.template.web.rest;

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

//    @GetMapping("/{type}")
//    public DemandeDTO findOne(@PathVariable String type){
//        DemandeDTO demandeDTO = ;
//        Preconditions.checkBusinessLogique(counterDTO != null, ENTITY_NAME + " Counter does Not found!");
//        return counterDTO;
//    }
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
}
