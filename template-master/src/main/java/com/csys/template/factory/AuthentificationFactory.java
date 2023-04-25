package com.csys.template.factory;

import com.csys.template.domain.Authentification;
import com.csys.template.domain.Counter;
import com.csys.template.dto.AuthentificationDTO;
import com.csys.template.dto.CounterDTO;



import java.util.ArrayList;
import java.util.List;

public class AuthentificationFactory {
    public static AuthentificationDTO authentificationToauthentificationDTO(Authentification authentication){
        AuthentificationDTO authentificationDTO = new AuthentificationDTO();
//        authentificationDTO.setId(authentication.getId());
        authentificationDTO.setAdress(authentication.getAdress());
        authentificationDTO.setCode(authentication.getCode());
        authentificationDTO.setRole(authentication.getRole());

        return authentificationDTO;
    }

    public static Authentification authentificationDTOToauthentification(AuthentificationDTO authentificationDTO){
        Authentification authentification = new Authentification();
//        authentification.setId(authentificationDTO.getId());
        authentification.setAdress(authentificationDTO.getAdress());
        authentification.setCode(authentificationDTO.getCode());
        authentification.setRole(authentificationDTO.getRole());

        return authentification;
    }

    public static List<AuthentificationDTO> authentificationsToauthentificationsDTO(List<Authentification> authentifications){
        List<AuthentificationDTO> authentificationDTOS = new ArrayList<AuthentificationDTO>();
        for(Authentification auth : authentifications){
            authentificationDTOS.add(authentificationToauthentificationDTO(auth));
        }
        return authentificationDTOS;
    }

    public static List<Authentification> authentificationsDTOToauthentifications(List<AuthentificationDTO> authentificationDTOS){
        List<Authentification> auth = new ArrayList<Authentification>();
        for(AuthentificationDTO authen : authentificationDTOS){
            auth.add(authentificationDTOToauthentification(authen));
        }
        return auth;
    }
}
