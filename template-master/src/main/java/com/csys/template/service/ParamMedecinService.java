package com.csys.template.service;

import com.csys.template.dto.MedecinDTO;
import com.csys.template.web.rest.errors.IllegalBusinessLogiqueException;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@Service("ParamServiceMedecin")
@DefaultProperties(ignoreExceptions = {IllegalBusinessLogiqueException.class})
public class ParamMedecinService {

    private final Logger log = LoggerFactory.getLogger(com.csys.template.service.ParamServiceClient.class);

    private final RestTemplate restTemplate;

    @Value("${paramService.base-uri}")
    private String baseUri;

    @Value("${paramService.medecin}")
    private String uriMedecin;


    public ParamMedecinService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @HystrixCommand(fallbackMethod = "serviceFindOneFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public MedecinDTO serviceFindOne(Integer code) {
        log.debug("Sending request to service to findOne");
        ResponseEntity<MedecinDTO> medecine = restTemplate.getForEntity(baseUri + uriMedecin + "/" + code, MedecinDTO.class);
        return medecine.getBody();
    }

    private MedecinDTO serviceFindOneFallback(String name) {
        log.error("falling back serviceFindOneFallback");
        return null;
    }

}
