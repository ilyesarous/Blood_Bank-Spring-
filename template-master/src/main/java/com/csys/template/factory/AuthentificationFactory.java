package com.csys.template.factory;

import com.csys.template.domain.Authentification;
import com.csys.template.dto.AuthentificationDTO;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthentificationFactory {


    public static String crypter (String code)
    {
        String ch="";
        for ( int i = 0; i < code.length(); ++i ){
            char c = code.charAt(i);
            Integer j = (int) c;
            j=j+3;
            if (j>122)
            {
                j=(j-122)+96;
            }
            String stringA = Character.toString(j);
            ch= ch+stringA;
        }
        return  ch;
    }

    public static String decrypter (String code)
    {
        String ch="";
        for ( int i = 0; i < code.length(); ++i ){
            char c = code.charAt(i);
            Integer j = (int) c;
            j=j-3;
            if (j<97)
            {
                j=123-(97-j);
            }
            String stringA = Character.toString(j);
            ch= ch+stringA;
        }
        return  ch;
    }
    public static AuthentificationDTO authentificationToauthentificationDTO(Authentification authentication){
        AuthentificationDTO authentificationDTO = new AuthentificationDTO();
        LocalDate d=authentication.getDatecreate();
        String date=d.toString();
        authentificationDTO.setAdress(authentication.getAdress());
        String code= authentication.getCode();
        String ch=decrypter(code);
        authentificationDTO.setCode(ch);
        authentificationDTO.setDatecreate(date);
        authentificationDTO.setActif(authentication.getActif());
        authentificationDTO.setName(authentication.getName());
        authentificationDTO.setRole(authentication.getRole());

        return authentificationDTO;
    }

    public static Authentification authentificationDTOToauthentification(AuthentificationDTO authentificationDTO){
        Authentification authentification = new Authentification();
        LocalDate d = LocalDate.now();

        authentification.setAdress(authentificationDTO.getAdress());
        String code= authentificationDTO.getCode();
         String ch= crypter(code);
        authentification.setCode(ch);
        authentification.setDatecreate(d);
        authentification.setActif(authentificationDTO.getActif());
        authentification.setName(authentificationDTO.getName());
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
