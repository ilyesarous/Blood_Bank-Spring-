package com.csys.template.service;

import com.csys.template.domain.Authentification;
import com.csys.template.dto.AuthentificationDTO;
import com.csys.template.factory.AuthentificationFactory;
import com.csys.template.repository.AuthentificationRepository;
import com.csys.template.repository.UserService;
import com.csys.template.util.Preconditions;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AuthentificationService implements UserService {

    private final AuthentificationRepository authentificationRepository;
    private final EmailService emailService;

    public AuthentificationService(AuthentificationRepository authentificationRepository, EmailService emailService) {
        this.authentificationRepository = authentificationRepository;
        this.emailService = emailService;
    }

    public List<AuthentificationDTO> findAll() {
        return AuthentificationFactory.authentificationsToauthentificationsDTO(authentificationRepository.findAll());
    }

    @Transactional(readOnly = true)
    public AuthentificationDTO findByAdress(String address, String code) {
        String codeDe = AuthentificationFactory.crypter(code);
        Authentification authentification = authentificationRepository.findByAddressAndCode(address, codeDe);
        Preconditions.checkBusinessLogique(authentification != null, "Address Not found!" + authentification);

        return AuthentificationFactory.authentificationToauthentificationDTO(authentification);
    }


    public AuthentificationDTO updateUser(AuthentificationDTO authentificationDTO) {
        Authentification authentification = authentificationRepository.findByAddress(authentificationDTO.getAdress());
        Preconditions.checkBusinessLogique(authentification != null, "User does not exists!");
        authentificationDTO.setAdress(authentification.getAdress());
        authentificationDTO.setRole(authentification.getRole());
        authentificationDTO.setActif(authentification.getActif());
        authentificationDTO.setDatecreate(authentification.getDatecreate().toString());
        authentificationRepository.save(AuthentificationFactory.authentificationDTOToauthentification(authentificationDTO));
        return authentificationDTO;
    }

    public AuthentificationDTO updatePassword(AuthentificationDTO authentificationDTO) {
        Authentification authentification = authentificationRepository.findByAddress(authentificationDTO.getAdress());
        Preconditions.checkBusinessLogique(authentification != null, "User does not exists!");
        authentificationDTO.setAdress(authentification.getAdress());
        authentificationDTO.setRole(authentification.getRole());
        authentificationDTO.setActif(authentification.getActif());
        authentificationDTO.setName(authentification.getName());
        authentificationDTO.setDatecreate(authentification.getDatecreate().toString());
        authentificationRepository.save(AuthentificationFactory.authentificationDTOToauthentification(authentificationDTO));
        return authentificationDTO;
    }


    @Override
    public ResponseEntity<?> saveUser(AuthentificationDTO authentificationDTO) {
        Authentification auth = authentificationRepository.findByAddress(authentificationDTO.getAdress());
        Preconditions.checkBusinessLogique(auth == null, "address already exists!");
        //generate password
        String password = RandomStringUtils.randomAlphabetic(8);//mehich tsirelha cryptage
        authentificationDTO.setCode(password);

        Authentification authentification = AuthentificationFactory.authentificationDTOToauthentification(authentificationDTO);
        authentification = authentificationRepository.save(authentification);
        //generating Email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(authentification.getAdress());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("You Blood-Bank account has been created!, you email is: " + authentificationDTO.getAdress() +
                " and your password is: " + authentificationDTO.getCode() + " please click here to Change your password: "
                + "http://localhost:3000/update_password?");
        emailService.sendEmail(mailMessage);

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

}
