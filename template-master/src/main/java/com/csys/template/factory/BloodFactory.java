package com.csys.template.factory;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Patient;
import com.csys.template.dto.BloodDTO;
import com.csys.template.dto.PatientDTO;

import java.util.ArrayList;
import java.util.List;

public class BloodFactory {

    public static Blood bloodDTOToBlood(BloodDTO bloodDTO) {
        Blood blood = new Blood();

        blood.setCodeBlood(bloodDTO.getCodeBlood());
        blood.setBloodGrp(bloodDTO.getBloodGrp());
        blood.setBloodType(bloodDTO.getBloodType());
        blood.setGivenTo(bloodDTO.getGivenTo());
        blood.setReceivedFrom(bloodDTO.getReceivedFrom());
        return blood;
    }

    public static BloodDTO bloodToBloodDTO(Blood blood){
        BloodDTO bloodDTO = new BloodDTO();

        bloodDTO.setCodeBlood(blood.getCodeBlood());
        bloodDTO.setBloodGrp(blood.getBloodGrp());
        bloodDTO.setBloodType(blood.getBloodType());
        bloodDTO.setGivenTo(blood.getGivenTo());
        bloodDTO.setReceivedFrom(blood.getReceivedFrom());

        return bloodDTO;
    }

    public static BloodDTO lazyBloodToBloodDTO(Blood blood){
        BloodDTO bloodDTO = new BloodDTO();

        bloodDTO.setCodeBlood(blood.getCodeBlood());
        bloodDTO.setBloodGrp(blood.getBloodGrp());
        bloodDTO.setBloodType(blood.getBloodType());
        bloodDTO.setGivenTo(blood.getGivenTo());
        bloodDTO.setReceivedFrom(blood.getReceivedFrom());

        return bloodDTO;
    }
    public static List<BloodDTO> bloodsToBloodsDTO(List<Blood> bloodList){
        List<BloodDTO> bloodDTOList = new ArrayList<BloodDTO>();
        for(Blood blood : bloodList){
            bloodDTOList.add(lazyBloodToBloodDTO(blood));
        }
        return bloodDTOList;
    }

}
