package com.csys.template.service;

import com.csys.template.dto.ServiceDTO;
import com.csys.template.web.rest.errors.IllegalBusinessLogiqueException;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Farouk
 */
@RefreshScope
@Service("ParamServiceClient")
@DefaultProperties(ignoreExceptions = {IllegalBusinessLogiqueException.class})
public class ParamServiceClient {

    private final Logger log = LoggerFactory.getLogger(ParamServiceClient.class);

    private final RestTemplate restTemplate;

    @Value("${paramService.base-uri}")
    private String baseUri;


    @Value("${paramService.service}")
    private String uriService;


    public ParamServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @HystrixCommand(fallbackMethod = "serviceFindOneFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ServiceDTO serviceFindOne(Integer id) {
        log.debug("Sending request to service to findOne");
        ResponseEntity<ServiceDTO> service = restTemplate.getForEntity(baseUri + uriService + "/" + id, ServiceDTO.class);
        return service.getBody();
    }

    private ServiceDTO serviceFindOneFallback(Integer id) {
        log.error("falling back serviceFindOneFallback");
        return null;
    }

    /*@HystrixCommand(fallbackMethod = "societeFindAllFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })*/


    //juste pour voir le post
    //  -------------------------------------------------------------------------------------
   /* @HystrixCommand(fallbackMethod = "findCabinetByCodeInFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })
    public Collection<CabinetDTO> findCabinetByCodeIn(Integer[] codes) {
        log.debug("Sending request to findCabinetByCodeIn");
        HttpEntity<Integer[]> entity = new HttpEntity<>(codes);
        ResponseEntity<Collection<CabinetDTO>> cabinets = restTemplate.exchange(baseUri + uriCabinet + "/findByCodeIn", HttpMethod.POST, entity, new ParameterizedTypeReference<Collection<CabinetDTO>>() {
        });
        return cabinets.getBody();
    }

    private Collection<CabinetDTO> findCabinetByCodeInFallBack(Integer[] codes) {
        Collection<CabinetDTO> cabinets = new ArrayList();
        log.error("falling back findCabinetByCodeInFallBack");
        return cabinets;
    }*/

    // -------------------------------------------------------------------------

}

