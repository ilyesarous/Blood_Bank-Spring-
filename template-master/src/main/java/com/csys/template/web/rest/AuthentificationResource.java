package com.csys.template.web.rest;

import com.csys.template.dto.AuthentificationDTO;
import com.csys.template.dto.CounterDTO;
import com.csys.template.service.AuthentificationService;
import com.csys.template.util.Preconditions;
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
@RequestMapping("/authentification")
public class AuthentificationResource {

    private final AuthentificationService authentificationService;

    public AuthentificationResource(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }
    @GetMapping
    public List<AuthentificationDTO> getAll(){
        return authentificationService.findAll();
    }
    @GetMapping("/{address}/{code}")
    public AuthentificationDTO findUser(@PathVariable String address ,@PathVariable String code){
        AuthentificationDTO authentificationDTO = authentificationService.findByAdress(address,code);
        Preconditions.checkBusinessLogique(authentificationDTO != null, ENTITY_NAME + " Counter does Not found!");
        return authentificationDTO;
    }
    @PostMapping
    public ResponseEntity<AuthentificationDTO> addUser (@RequestBody @Valid AuthentificationDTO authentificationDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
       if(authentificationDTO.getCode()== null){
           bindingResult.addError(new FieldError(ENTITY_NAME, "code", "You can not add a counter with id"));
           throw new MethodArgumentNotValidException(null, bindingResult);
       }
        AuthentificationDTO auth = authentificationService.addAuthentification(authentificationDTO);
        return ResponseEntity.created(new URI("/authentification"+ auth.getAdress())).body(auth);
    }

    @PutMapping("/{adress}")
    public ResponseEntity<AuthentificationDTO> updateAuth(@RequestBody @Valid AuthentificationDTO authentificationDTO, @Valid @PathVariable String adress)
            throws URISyntaxException {
        AuthentificationDTO auth = authentificationService.updateCounter(authentificationDTO);
        return ResponseEntity.created(new URI("/donation" + auth.getAdress())).body(auth);
    }
}
