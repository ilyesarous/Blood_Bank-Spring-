package com.csys.template.service;

import com.csys.template.domain.Blood;
import com.csys.template.dto.BloodDTO;
import com.csys.template.factory.BloodFactory;
import com.csys.template.repository.BloodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BloodService {

    private final BloodRepository bloodRepository;

    public BloodService(BloodRepository bloodRepository) {
        this.bloodRepository = bloodRepository;
    }

    @Transactional(readOnly = true)
    public List<BloodDTO> findAll() {
        return BloodFactory.bloodsToBloodsDTO(bloodRepository.findAll());
    }

    @Transactional(readOnly = true)
    public BloodDTO findBloodByType(String type) {
        Blood blood = bloodRepository.findBybloodType(type);
        return BloodFactory.bloodToBloodDTO(blood);
    }

    @Transactional(readOnly = true)
    public String findTypeByBloodCode(Integer codeBlood) {
        Blood blood = bloodRepository.findByCodeBlood(codeBlood);
        return blood.getBloodType();
    }

    @Transactional(readOnly = true)
    public List<String> findAllTypes() {
        List<String> types = (findAll().stream()
                .map(BloodDTO::getBloodType)
                .filter(bloodType -> bloodType != null)
                .distinct()
                .toList());
        return types;
    }
    @Transactional(readOnly = true)
    public List<String> findAllGroups() {
        List<String> groups = (findAll().stream()
                .map(BloodDTO::getBloodGrp)
                .filter(bloodGrp -> bloodGrp != null)
                .distinct()
                .toList());
        return groups;
    }

    public BloodDTO addBlood(BloodDTO bloodDTO) {
        Blood blood = BloodFactory.bloodDTOToBlood(bloodDTO);
        blood = bloodRepository.save(blood);
        return BloodFactory.bloodToBloodDTO(blood);
    }

    public BloodDTO updateBlood(BloodDTO bloodDTO){
        Blood bloodInDB = bloodRepository.findByCodeBlood(bloodDTO.getCodeBlood());
        bloodDTO.setCodeBlood(bloodInDB.getCodeBlood());
        bloodDTO.setCreationDate(bloodInDB.getCreationDate());
        return BloodFactory.bloodToBloodDTO((bloodRepository.save(BloodFactory.bloodDTOToBlood(bloodDTO))));
    }


}
