package com.csys.template.web.rest;

import com.csys.template.domain.Blood;
import com.csys.template.dto.BloodDTO;
import com.csys.template.search.BloodSearch;
import com.csys.template.service.BloodService;
import com.csys.template.util.RestPreconditions;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

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
    public List<BloodDTO> getAll(@RequestParam(value = "group", required = false) String group,
                                  @RequestParam(value = "given", required = false) String given,
                                  @RequestParam(value = "receive", required = false) String receive){
        String codeFromGiven = "";
        String codeFromReceive= "";
        if(!Objects.equals(given, ""))
            codeFromGiven = codeFromGiven + bloodService.findBloodCodeByType(given);
        if(!Objects.equals(receive, ""))
            codeFromReceive = codeFromReceive + bloodService.findBloodCodeByType(receive);
        Specification<Blood> specification = BloodSearch.getSearch(group, codeFromGiven, codeFromReceive);
        return bloodService.findAll(specification);
    }

    @GetMapping("/c")
    public Blood findblood(@RequestBody BloodDTO b){
        return bloodService.findBya(b.getBloodGrp(), b.getRhesus());
    }
    @GetMapping("/specifc-type/{code}")
    public String getTypeByCodeBlood(@Valid @PathVariable Integer code) {
        return bloodService.findTypeByBloodCode(code);
    }
    @GetMapping("/type/{type}")
    public Integer getCodeByBloodType(@Valid @PathVariable String type) {
        return bloodService.findBloodCodeByType(type);
    }

    @GetMapping("/type")
    public List<String> getAllTypes() {
        return bloodService.findTypes();
    }

    @GetMapping("/groups")
    public List<String> getAllGroups() {
        return bloodService.findAllGroups();
    }

    @PostMapping
    public ResponseEntity<BloodDTO> addBlood(@RequestBody @Valid BloodDTO bloodDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if (bloodDTO.getCodeBlood() != null) {
            bindingResult.addError(new FieldError(ENTITY_NAME, "codeBlood", "You can not add blood with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        BloodDTO c = bloodService.addBlood(bloodDTO);
        return ResponseEntity.created(new URI("/blood" + c.getCodeBlood())).body(c);
    }

    @PutMapping("/{code}")
    public ResponseEntity<BloodDTO> updateBlood(@RequestBody @Valid BloodDTO bloodDTO, @Valid @PathVariable Integer code)
            throws URISyntaxException {
        BloodDTO c = bloodService.updateBlood(bloodDTO);
        return ResponseEntity.created(new URI("/blood" + c.getCodeBlood())).body(c);
    }

    @PutMapping("/status/{code}")
    public ResponseEntity<BloodDTO> updateBloodStatus(@Valid @PathVariable Integer code)
            throws URISyntaxException {
        BloodDTO c = bloodService.updateStatusBlood(code);
        return ResponseEntity.created(new URI("/blood" + c.getCodeBlood())).body(c);
    }

}
