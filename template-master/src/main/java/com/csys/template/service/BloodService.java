package com.csys.template.service;

import com.csys.template.domain.Blood;
import com.csys.template.dto.BloodDTO;
import com.csys.template.factory.BloodFactory;
import com.csys.template.repository.BloodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BloodService {

    private final BloodRepository bloodRepository;

    public BloodService(BloodRepository bloodRepository) {
        this.bloodRepository = bloodRepository;
    }

    @Transactional(readOnly = true)
    public List<BloodDTO> findAll(){
        return BloodFactory.bloodsToBloodsDTO(bloodRepository.findAll());
    }
    @Transactional(readOnly = true)
    public BloodDTO findBloodByType(String type){
        Blood blood = bloodRepository.findByCodeBlood(type);
        return BloodFactory.bloodToBloodDTO(blood);
    }
    public Blood addBlood(BloodDTO bloodDTO) {
        Blood blood = BloodFactory.bloodDTOToBlood(bloodDTO);
        blood = bloodRepository.save(blood);
        return blood;
    }


}
