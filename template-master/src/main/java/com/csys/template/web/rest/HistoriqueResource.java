package com.csys.template.web.rest;

import com.csys.template.domain.donations_history;
import com.csys.template.dto.donations_historyDTO;
import com.csys.template.service.PatientHistoriqueService;
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
@RequestMapping("/historique")
public class HistoriqueResource {
    private  final PatientHistoriqueService patientHistoriqueService;

    public HistoriqueResource(PatientHistoriqueService patientHistoriqueService) {
        this.patientHistoriqueService = patientHistoriqueService;
    }

    @GetMapping("/{code}")
    public List<donations_historyDTO> getAllPatientsWithBloodCode(@PathVariable String code){
        List<donations_historyDTO> patientHistoriqueDTOS = patientHistoriqueService.findHistory(code);
        RestPreconditions.checkFound(patientHistoriqueDTOS,ENTITY_NAME + "Code not found!");
        return patientHistoriqueDTOS;
    }

    @PostMapping
    public ResponseEntity<donations_history> addPatient(@RequestBody donations_historyDTO patientDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(patientDTO.getCode()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "code", " You can not add patient with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        donations_history p = patientHistoriqueService.addHistorique(patientDTO);
        return ResponseEntity.created(new URI("/historique"+ p.getCode())).body(p);
    }

    @PutMapping
    public ResponseEntity<donations_history> updatePatient(@RequestBody donations_historyDTO patientDTO, BindingResult bindingResult) throws MethodArgumentNotValidException, URISyntaxException {
        if(patientDTO.getCode()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "code", "Put does not allow patient with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        donations_history p = patientHistoriqueService.updateHistoriy(patientDTO);
        return ResponseEntity.created(new URI("/historique"+ p.getCode())).body(p);
    }
}
