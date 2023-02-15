package com.csys.template.web.rest;

import com.csys.template.domain.Counter;
import com.csys.template.dto.CounterDTO;
import com.csys.template.service.CounterService;
import com.csys.template.util.RestPreconditions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/counter")
public class CounterResource {

    private final CounterService counterService;

    public CounterResource(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping
    public List<CounterDTO> getAll(){
        return counterService.findAll();
    }

    @GetMapping("/{type}")
    public CounterDTO findOne(@PathVariable String type){
        CounterDTO counterDTO = counterService.findCounterByType(type);
        RestPreconditions.checkFound(counterDTO,ENTITY_NAME + " Not found!");
        return counterDTO;
    }
    @PostMapping
    public ResponseEntity<Counter> addCounter(@RequestBody CounterDTO counterDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException, URISyntaxException {
        if(counterDTO.getId()!= null){
            bindingResult.addError(new FieldError(ENTITY_NAME, "id", "You can not add a counter with id"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        Counter c = counterService.addCounter(counterDTO);
        return ResponseEntity.created(new URI("/counter"+ c.getType())).body(c);
    }

}
