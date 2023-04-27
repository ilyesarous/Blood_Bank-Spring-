package com.csys.template.service;

import com.csys.template.domain.Authentification;
import com.csys.template.dto.AuthentificationDTO;
import com.csys.template.factory.AuthentificationFactory;
import com.csys.template.repository.AuthentificationRepository;
import com.csys.template.util.Preconditions;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthentificationService {

    private final AuthentificationRepository authentificationRepository;

    public AuthentificationService(AuthentificationRepository authentificationRepository) {
        this.authentificationRepository = authentificationRepository;
    }

    public List<AuthentificationDTO> findAll(){
        return AuthentificationFactory.authentificationsToauthentificationsDTO(authentificationRepository.findAll());
    }

    @Transactional(readOnly = true)
    public AuthentificationDTO findByAdress(String address , String code) {
        String codeDe=AuthentificationFactory.crypter(code);
        Authentification authentification = authentificationRepository.findByAddressAndCode(address,codeDe);
        Preconditions.checkBusinessLogique(authentification != null,"Adress Not found!"+ authentification);

        return AuthentificationFactory.authentificationToauthentificationDTO(authentification);
    }

    public AuthentificationDTO addAuthentification(AuthentificationDTO authentificationDTO) {
        Authentification authentification = AuthentificationFactory.authentificationDTOToauthentification(authentificationDTO);
        authentification = authentificationRepository.save(authentification);
        return AuthentificationFactory.authentificationToauthentificationDTO(authentification);
    }

    public AuthentificationDTO updateCounter(AuthentificationDTO authentificationDTO){
        Authentification authentification = authentificationRepository.findByAddress(authentificationDTO.getAdress());
        com.google.common.base.Preconditions.checkArgument (authentification != null, "Counter has been deleted");
        authentificationDTO.setAdress(authentification.getAdress());
        authentificationRepository.save(AuthentificationFactory.authentificationDTOToauthentification(authentificationDTO));
        return authentificationDTO;
    }


}
