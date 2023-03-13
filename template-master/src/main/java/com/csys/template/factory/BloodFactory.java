package com.csys.template.factory;

import com.csys.template.domain.Blood;
import com.csys.template.dto.BloodDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BloodFactory {

    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static Blood bloodDTOToBlood(BloodDTO bloodDTO) {
        Blood blood = new Blood();
        LocalDate d = LocalDate.now();
        blood.setCodeBlood(bloodDTO.getCodeBlood());
        blood.setBloodGrp(bloodDTO.getBloodGrp());
        blood.setRhesus(bloodDTO.getRhesus());
        blood.setGivenTo(bloodDTO.getGivenTo());
        blood.setReceivedFrom(bloodDTO.getReceivedFrom());
        blood.setCreationDate(d);
        blood.setUserCreate(getUserAuthenticated());
        blood.setActive(1);
        return blood;
    }

    public static BloodDTO bloodToBloodDTO(Blood blood){
        BloodDTO bloodDTO = new BloodDTO();

        bloodDTO.setCodeBlood(blood.getCodeBlood());
        bloodDTO.setBloodGrp(blood.getBloodGrp());
        bloodDTO.setRhesus(blood.getRhesus());
        bloodDTO.setGivenTo(blood.getGivenTo());
        bloodDTO.setReceivedFrom(blood.getReceivedFrom());
        bloodDTO.setUserCreate(blood.getUserCreate());
        bloodDTO.setActive(blood.getActive());

        return bloodDTO;
    }

    public static BloodDTO lazyBloodToBloodDTO(Blood blood){
        BloodDTO bloodDTO = new BloodDTO();

        bloodDTO.setCodeBlood(blood.getCodeBlood());
        bloodDTO.setBloodGrp(blood.getBloodGrp());
        bloodDTO.setRhesus(blood.getRhesus());
        bloodDTO.setGivenTo(blood.getGivenTo());
        bloodDTO.setReceivedFrom(blood.getReceivedFrom());
        bloodDTO.setCreationDate(blood.getCreationDate());
        bloodDTO.setUserCreate(blood.getUserCreate());
        bloodDTO.setActive(blood.getActive());
       // bloodDTO.setChampIlyes(blood.getCodeBlood() + blood.getRhesus());
        return bloodDTO;
    }
    public static List<BloodDTO> bloodsToBloodsDTO(List<Blood> bloodList){
        List<BloodDTO> bloodDTOList = new ArrayList<BloodDTO>();
        for(Blood blood : bloodList){
            bloodDTOList.add(lazyBloodToBloodDTO(blood));
        }
        return bloodDTOList;
    }
    public static List<Blood> bloodsDTOToBloods(List<BloodDTO> bloodDTOList){
        List<Blood> bloodList = new ArrayList<Blood>();
        for(BloodDTO blood : bloodDTOList){
            bloodList.add(bloodDTOToBlood(blood));
        }
        return bloodList;
    }

    public static Blood statusChangeHandler(Blood blood){
        blood.setCodeBlood(blood.getCodeBlood());
        blood.setBloodGrp(blood.getBloodGrp());
        blood.setRhesus(blood.getRhesus());
        blood.setGivenTo(blood.getGivenTo());
        blood.setReceivedFrom(blood.getReceivedFrom());
        blood.setCreationDate(blood.getCreationDate());
        blood.setUserCreate(blood.getUserCreate());
        if (blood.getActive() == 1)
            blood.setActive(0);
        else blood.setActive(1);
        return blood;
    }
}
