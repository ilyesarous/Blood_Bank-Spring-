package com.csys.template.web.rest;

import com.csys.template.dto.MedecinDTO;
import com.csys.template.dto.ServiceDTO;
import com.csys.template.service.ParamMedecinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param")
public class ParamResource {

    /*private final ParamServiceClient paramServiceClient;

    public ParamResource(ParamServiceClient paramServiceClient) {
        this.paramServiceClient = paramServiceClient;
    }*/

    private  final ParamMedecinService paramMedecinService;

    public ParamResource(ParamMedecinService paramMedecinService) {
        this.paramMedecinService = paramMedecinService;
    }

    @GetMapping("/{nom}")
    public MedecinDTO getOne(@PathVariable String nom){
        return paramMedecinService.serviceFindOne(nom);
    }
}
