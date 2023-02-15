package com.csys.template.web.rest;

import com.csys.template.domain.Patient;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.PatientDTO;
import com.csys.template.service.CounterService;
import com.csys.template.service.PatientService;
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
@RequestMapping("/patient")
public class PatientResource {

    private final PatientService patientService;
    private final CounterService counterService;

    public PatientResource(PatientService patientService, CounterService counterService) {
        this.patientService = patientService;
        this.counterService = counterService;
    }

    @GetMapping
    public List<PatientDTO> getAll(){
        return patientService.findAll();
    }

    @GetMapping("/{code}")
    public PatientDTO findOne(@PathVariable String code){
        PatientDTO patientDTO = patientService.findPatientByCode(code);
        RestPreconditions.checkFound(patientDTO,ENTITY_NAME + " Not found!");
        return patientDTO;
    }

    @GetMapping("/blood-code/{code}")
    public List<PatientDTO> getAllPatientsWithBloodCode(@PathVariable Integer code){
        List<PatientDTO> patientDTOS = patientService.findPatientsWithBloodCode(code);
        RestPreconditions.checkFound(patientDTOS,ENTITY_NAME + "Code not found!");
        return patientDTOS;
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDTO patientDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(patientDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", " You can not add patient with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        CounterDTO counter = counterService.findCounterByType("patient");
        patientDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        Patient p = patientService.addPatient(patientDTO);
        return ResponseEntity.created(new URI("/patient"+ p.getCode())).body(p);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientDTO patientDTO, BindingResult bindingResult) throws MethodArgumentNotValidException, URISyntaxException {
        if(patientDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", "Put does not allow patient with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        Patient p = patientService.updatePatient(patientDTO);
        return ResponseEntity.created(new URI("/patient"+ p.getCode())).body(p);
    }
}
