package com.csys.template.web.rest;

import com.csys.template.domain.Blood;
import com.csys.template.dto.BloodDTO;
import com.csys.template.service.BloodService;
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

import static com.csys.template.TemplateApplication.log;
import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/blood")
@CrossOrigin
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
    public Integer findBloodCodeByType(@PathVariable String type){
        Integer bloodCode = bloodService.findBloodCodeByType(type);
        RestPreconditions.checkFound(bloodCode,ENTITY_NAME + " Not found!");
        return bloodCode;
    }

    @GetMapping("/type/{code}")
    public String getTypeByCodeBlood(@Valid @PathVariable Integer code){
        return bloodService.findTypeByBloodCode(code);
    }

    @GetMapping("/type")
    public List<String> getAllTypes(){
        return bloodService.findAllTypes();
    }

    @GetMapping("/groups")
    public List<String> getAllGroups(){
        return bloodService.findAllGroups();
    }

    @PostMapping
    public ResponseEntity<BloodDTO> addBlood(@RequestBody @Valid BloodDTO bloodDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(bloodDTO.getCodeBlood()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "codeBlood", "You can not add blood with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        List<BloodDTO> list = getAll();
        for(BloodDTO blood : list){
            if(blood.getBloodType().equals(bloodDTO.getBloodType())) {
               return  null;
            }
        }
        BloodDTO c = bloodService.addBlood(bloodDTO);
        return ResponseEntity.created(new URI("/blood"+ c.getCodeBlood())).body(c);
    }

    @PutMapping("/{code}")
    public ResponseEntity<BloodDTO> updateBlood(@RequestBody @Valid BloodDTO bloodDTO,@Valid @PathVariable Integer code, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        BloodDTO c = bloodService.updateBlood(bloodDTO);
        return ResponseEntity.created(new URI("/blood"+ c.getCodeBlood())).body(c);
    }

    @PutMapping("/status/{code}")
    public ResponseEntity<BloodDTO> updateBloodStatus( @Valid @PathVariable Integer code)
            throws MethodArgumentNotValidException, URISyntaxException {
        BloodDTO c = bloodService.updateStatusBlood(code);
        return ResponseEntity.created(new URI("/blood"+ c.getCodeBlood())).body(c);
    }




}
