package com.csys.template.web.rest;

import com.csys.template.dto.BonReceptionDTO;
import com.csys.template.dto.CounterDTO;
import com.csys.template.service.BonReceptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bonReception")
public class BonReceptionResource {
    private final BonReceptionService bonReceptionService;

    public BonReceptionResource(BonReceptionService bonReceptionService) {
        this.bonReceptionService = bonReceptionService;
    }

    @GetMapping
    public List<BonReceptionDTO> getAll(){
        return bonReceptionService.findAll();
    }

    @GetMapping("/{code}")
    public List<BonReceptionDTO> getByCode(String code){
        List<BonReceptionDTO> bonReceptionDTOS=bonReceptionService.findByCode(code);
        return bonReceptionDTOS;
    }


}
