package com.csys.template.web.rest;

import com.csys.template.domain.DonationsHistory;
import com.csys.template.dto.DonationsHistoryDTO;
import com.csys.template.service.PatientHistoriqueService;
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

//@CrossOrigin
@RestController
@RequestMapping("/historique")
public class HistoriqueResource {
    private  final PatientHistoriqueService patientHistoriqueService;

    public HistoriqueResource(PatientHistoriqueService patientHistoriqueService) {
        this.patientHistoriqueService = patientHistoriqueService;
    }

    @GetMapping("/{code}")
    public List<DonationsHistoryDTO> getAllPatientsWithBloodCode(@PathVariable String code){
        List<DonationsHistoryDTO> patientHistoriqueDTOS = patientHistoriqueService.findHistory(code);
        return patientHistoriqueDTOS;
    }

    @PostMapping
    public ResponseEntity<DonationsHistoryDTO> addPatient(@RequestBody DonationsHistoryDTO patientDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(patientDTO.getCode()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "code", " You can not add patient with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        DonationsHistoryDTO p = patientHistoriqueService.addHistorique(patientDTO);
        return ResponseEntity.created(new URI("/historique"+ p.getCode())).body(p);
    }

    @PutMapping("/{code}")
    public ResponseEntity<DonationsHistoryDTO> updatePatient(@PathVariable String code , @RequestBody @Valid DonationsHistoryDTO patientDTO, BindingResult bindingResult) throws MethodArgumentNotValidException, URISyntaxException {
        if(patientDTO.getCode()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "code", "Put does not allow patient with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        DonationsHistoryDTO p = patientHistoriqueService.updateHistoriy(patientDTO);
        return ResponseEntity.created(new URI("/historique"+ p.getCode())).body(p);
    }
}
