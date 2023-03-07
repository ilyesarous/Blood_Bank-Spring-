package com.csys.template.service;

import com.csys.template.domain.Blood;
import com.csys.template.dto.BloodDTO;
import com.csys.template.factory.BloodFactory;
import com.csys.template.repository.BloodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class BloodService {

    private final BloodRepository bloodRepository;

    public BloodService(BloodRepository bloodRepository) {
        this.bloodRepository = bloodRepository;
    }
    @Transactional(readOnly = true)
    public List<BloodDTO> findAll() {
        List<Blood> bloods = bloodRepository.findAll();
        for (Blood b : bloods) {
            if(!Objects.equals(b.getGivenTo(), "-")){
                String[] given = b.getGivenTo().split(",");
                StringBuilder s = new StringBuilder(" ");
                for (String ch : given) {
                    if (!Objects.equals(ch, "")) {
                        s.append(findTypeByBloodCode(Integer.parseInt(ch))).append(",");
                    }
                }
                b.setGivenTo(s.substring(0, s.length() - 1).trim());
            }
            if(!Objects.equals(b.getReceivedFrom(), "-")){
                String[] received = b.getReceivedFrom().split(",");
                StringBuilder s1 = new StringBuilder(" ");
                for (String ch : received) {
                    if (!Objects.equals(ch, "")) {
                        s1.append(findTypeByBloodCode(Integer.parseInt(ch))).append(",");
                    }
                }
                b.setReceivedFrom(s1.substring(0, s1.length() - 1).trim());
            }
        }
        return BloodFactory.bloodsToBloodsDTO(bloods);
    }

    @Transactional(readOnly = true)
    public Integer findBloodCodeByType(String type) {
        if (bloodRepository.findBybloodType(type) != null) {
            return bloodRepository.findBybloodType(type).getCodeBlood();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public String findTypeByBloodCode(Integer codeBlood) {
        if (bloodRepository.findByCodeBlood(codeBlood) != null) {
            return bloodRepository.findByCodeBlood(codeBlood).getBloodType();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<String> findAllTypes() {
        return (findAll().stream()
                .map(BloodDTO::getBloodType)
                .filter(bloodType -> bloodType != null)
                .distinct()
                .toList());
    }

    @Transactional(readOnly = true)
    public List<String> findAllGroups() {
        return (findAll().stream()
                .map(BloodDTO::getBloodGrp)
                .filter(bloodGrp -> bloodGrp != null)
                .distinct()
                .toList());
    }

    public BloodDTO addBlood(BloodDTO bloodDTO) {
        if (!Objects.equals(bloodDTO.getGivenTo(), "-")) {
            String[] given = bloodDTO.getGivenTo().split(",");
            StringBuilder s = new StringBuilder(" ");
            for (String ch : given) {
                if (findBloodCodeByType(ch) != null) {
                    s.append(findBloodCodeByType(ch)).append(",");
                }
            }
            bloodDTO.setGivenTo(s.substring(0, s.length() - 1).trim());
        }

        if (!Objects.equals(bloodDTO.getReceivedFrom(), "-")) {
            String[] received = bloodDTO.getReceivedFrom().split(",");
            StringBuilder s1 = new StringBuilder(" ");
            for (String ch : received) {
                if (findBloodCodeByType(ch) != null) {
                    s1.append(findBloodCodeByType(ch)).append(",");
                }
            }
            bloodDTO.setReceivedFrom(s1.substring(0, s1.length() - 1).trim());
        }
        Blood blood = BloodFactory.bloodDTOToBlood(bloodDTO);
        blood = bloodRepository.save(blood);
        return BloodFactory.bloodToBloodDTO(blood);
    }

    public BloodDTO updateBlood(BloodDTO bloodDTO) {
        Blood bloodInDB = bloodRepository.findByCodeBlood(bloodDTO.getCodeBlood());
        bloodDTO.setCodeBlood(bloodInDB.getCodeBlood());
        bloodDTO.setCreationDate(bloodInDB.getCreationDate());
        bloodDTO.setUserCreate(bloodInDB.getUserCreate());

        if (!Objects.equals(bloodDTO.getGivenTo(), "-")) {
            String[] given = bloodDTO.getGivenTo().split(",");
            StringBuilder s = new StringBuilder(" ");
            for (String ch : given) {
                if (findBloodCodeByType(ch) != null) {
                    s.append(findBloodCodeByType(ch)).append(",");
                }
            }
            bloodDTO.setGivenTo(s.substring(0, s.length() - 1).trim());
        }

        if (!Objects.equals(bloodDTO.getReceivedFrom(), "-")) {
            String[] received = bloodDTO.getReceivedFrom().split(",");
            StringBuilder s1 = new StringBuilder(" ");
            for (String ch : received) {
                if (findBloodCodeByType(ch) != null) {
                    s1.append(findBloodCodeByType(ch)).append(",");
                }
            }
            bloodDTO.setReceivedFrom(s1.substring(0, s1.length() - 1).trim());
        }

        return BloodFactory.bloodToBloodDTO((bloodRepository.save(BloodFactory.bloodDTOToBlood(bloodDTO))));
    }

    public BloodDTO updateStatusBlood(Integer code) {
        Blood bloodInDB = bloodRepository.findByCodeBlood(code);
        BloodFactory.statusChangeHandler(bloodInDB);
        return BloodFactory.bloodToBloodDTO((bloodRepository.save(bloodInDB)));
    }


}
