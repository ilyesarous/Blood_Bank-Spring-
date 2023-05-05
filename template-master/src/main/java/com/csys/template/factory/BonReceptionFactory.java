package com.csys.template.factory;

import com.csys.template.domain.BonReception;
import com.csys.template.domain.Stock;
import com.csys.template.dto.BonReceptionDTO;
import com.csys.template.dto.StockDTO;
import com.csys.template.dto.StockHistoryDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BonReceptionFactory {
    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static BonReceptionDTO bonReceptionToBonReceptionDTO(BonReception bonReception){
        BonReceptionDTO bonReceptionDTO = new BonReceptionDTO();
        bonReceptionDTO.setId(bonReception.getId());
        bonReceptionDTO.setBloodCode(bonReception.getBloodCode().toString());
        bonReceptionDTO.setCode(bonReception.getCode());
        bonReceptionDTO.setCodedonation(bonReception.getCodedonation());
        bonReceptionDTO.setUserCreate(bonReception.getUserCreate());
        bonReceptionDTO.setQuantity(bonReception.getQuantity());
        LocalDate d= bonReception.getDateCreate();
        String x=d.toString();
        bonReceptionDTO.setDateCreate(x);

        bonReceptionDTO.setDateperime(bonReception.getDateperime());


        return bonReceptionDTO;

    }

    public static BonReception BonReceptionDTOToBonReception(BonReceptionDTO bonReceptionDTO){
        BonReception bonReception = new BonReception();
        LocalDate d = LocalDate.now();
        bonReception.setId(bonReceptionDTO.getId());
        Integer bloodcode=Integer.parseInt(bonReceptionDTO.getBloodCode());

        bonReception.setBloodCode(bloodcode);
        bonReception.setCode(bonReceptionDTO.getCode());
        bonReception.setCodedonation(bonReceptionDTO.getCodedonation());
        bonReception.setUserCreate(getUserAuthenticated());
        bonReception.setQuantity(bonReceptionDTO.getQuantity());

        bonReception.setDateCreate(d);
        bonReception.setDateperime(bonReceptionDTO.getDateperime());


        return bonReception ;
    }

    public static List<BonReceptionDTO> bonReceptionSToBonReceptionsDTO(List<BonReception> bonReception){
        List<BonReceptionDTO> bonReceptionDTO = new ArrayList<BonReceptionDTO>();
        for(BonReception recept : bonReception){
            bonReceptionDTO.add(bonReceptionToBonReceptionDTO(recept));
        }
        return bonReceptionDTO;
    }

    public static List<BonReception> stocksDTOToStocks(List<BonReceptionDTO> bonReceptionDTOS){
        List<BonReception> bonReceptions = new ArrayList<BonReception>();
        for(BonReceptionDTO bonReceptionDTO : bonReceptionDTOS){
            bonReceptions.add(BonReceptionDTOToBonReception(bonReceptionDTO));

        }
        return bonReceptions;
    }
}
