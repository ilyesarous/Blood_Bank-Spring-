package com.csys.template.web.rest;

import com.csys.template.domain.Blood;
import com.csys.template.dto.BloodDTO;
import com.csys.template.service.BloodService;
import com.csys.template.util.RestPreconditions;
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
@RequestMapping("/blood")
public class BloodResource {

    private final BloodService bloodService;

    public BloodResource(BloodService bloodService) {
        this.bloodService = bloodService;
    }

    @GetMapping
    public List<BloodDTO> getAll(){
        return bloodService.findAll();
    }

    @GetMapping("/{type}")
    public BloodDTO findOne(@PathVariable String type){
        BloodDTO bloodDTO = bloodService.findBloodByType(type);
        RestPreconditions.checkFound(bloodDTO,ENTITY_NAME + " Not found!");
        return bloodDTO;
    }
    @PostMapping
    public ResponseEntity<Blood> addBlood(@RequestBody BloodDTO bloodDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(bloodDTO.getCodeBlood()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "codeBlood", "You can not add blood with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        Blood c = bloodService.addBlood(bloodDTO);
        return ResponseEntity.created(new URI("/blood"+ c.getCodeBlood())).body(c);
    }



}
