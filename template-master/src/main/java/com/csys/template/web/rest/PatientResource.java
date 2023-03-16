package com.csys.template.web.rest;

import com.csys.template.domain.Patient;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.PatientDTO;
import com.csys.template.search.PatientSearch;
import com.csys.template.service.CounterService;
import com.csys.template.service.PatientService;
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

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;


@RestController
@RequestMapping("/patient")
//@CrossOrigin
public class PatientResource {

    private final PatientService patientService;
    private final CounterService counterService;

    public PatientResource(PatientService patientService, CounterService counterService) {
        this.patientService = patientService;
        this.counterService = counterService;
    }

@GetMapping
public List<PatientDTO> getAll(@RequestParam(value = "lastNameAr", required = false) String name,
                             @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                             @RequestParam(value = "codepatient", required = false) String codepatient ){
    Specification<Patient> patient = PatientSearch.getSearch(name,phoneNumber,codepatient);

    return patientService.findAll(patient);

}

    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(patientDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", " You can not add patient with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        PatientDTO p = patientService.addPatient(patientDTO);
        return ResponseEntity.created(new URI("/patient"+ p.getCode())).body(p);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid PatientDTO patientDTO, @PathVariable String code, BindingResult bindingResult) throws MethodArgumentNotValidException, URISyntaxException {
        if(patientDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", "Put does not allow patient with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        Patient p = patientService.updatePatient(patientDTO);
        return ResponseEntity.created(new URI("/patient"+ p.getCode())).body(p);
    }
}
