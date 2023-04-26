package com.csys.template.service;

import com.csys.template.domain.Blood;
import com.csys.template.dto.BloodDTO;
import com.csys.template.factory.BloodFactory;
import com.csys.template.repository.BloodRepository;
import com.csys.template.util.Preconditions;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.csys.template.TemplateApplication.log;

@Service
@Transactional
public class BloodService {

    private final BloodRepository bloodRepository;

    public BloodService(BloodRepository bloodRepository) {
        this.bloodRepository = bloodRepository;
    }

    @Transactional(readOnly = true)
    public List<BloodDTO> findAll(Specification<Blood> specification) {
        log.debug("***find All***");
        List<Blood> bloods = bloodRepository.findAll(specification);
        Preconditions.checkBusinessLogique(bloods != null, "error.couldn't-find-bloods");
       // List<Integer> bloodsCodes = findCodes();
        List<String> bloodsTypes = findTypes();
        for (Blood b : bloods) {
            if (!Objects.equals(b.getGivenTo(), "-")) {
                String[] given = b.getGivenTo().split(",");
                StringBuilder s = new StringBuilder(" ");
                for (String ch : given) {
                    if (!Objects.equals(ch, "")) {
                        s.append(bloodsTypes.get(Integer.parseInt(ch)-1)).append(",");
                    }
                }
                b.setGivenTo(s.substring(0, s.length() - 1).trim());
            }
            if (!Objects.equals(b.getReceivedFrom(), "-")) {
                String[] received = b.getReceivedFrom().split(",");
                StringBuilder s1 = new StringBuilder(" ");
                for (String ch : received) {
                    if (!Objects.equals(ch, "")) {
                        s1.append(bloodsTypes.get(Integer.parseInt(ch)-1)).append(",");
                    }
                }
                b.setReceivedFrom(s1.substring(0, s1.length() - 1).trim());
            }
        }
        return BloodFactory.bloodsToBloodsDTO(bloods);
    }

    public List<Integer> findCodes(){
        log.debug("***find All Blood Codes***");
        List<Blood> bloods = bloodRepository.findAll();
        return bloods.stream()
                .map(Blood::getCodeBlood)
                .distinct()
                .toList();
    }
    public List<String> findTypes(){
        log.debug("***find All Types***");
        List<Integer> bloods = findCodes();
        List<String> types = new ArrayList<>();
        for(int i : bloods){
            types.add(findTypeByBloodCode(i));
        }
        return types;
    }

    public Blood findBya(String group, String rhesus) {
        log.debug("***find By Group And Rhesus***");
        Blood blood = bloodRepository.findBybloodGrpAndRhesus(group,rhesus);
        Preconditions.checkBusinessLogique(blood != null, "error.couldn't-find-blood");

        return blood;
    }

    @Transactional(readOnly = true)
    public Integer findBloodCodeByType(String ch) {
        log.debug("***find Blood Code By Type***");
        String rhesus;
        rhesus = ch.substring(ch.length() - 1);
        String group = ch.substring(0, ch.length() - 1);

        if (bloodRepository.findBybloodGrpAndRhesus(group, rhesus) != null) {
            return bloodRepository.findBybloodGrpAndRhesus(group, rhesus).getCodeBlood();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Blood findBloodByCode(Integer code) {
        return bloodRepository.findByCodeBlood(code);
    }

    @Transactional(readOnly = true)
    public String findTypeByBloodCode(Integer codeBlood) {
        log.debug("***find Type By Blood Code***");
        Blood blood = bloodRepository.findByCodeBlood(codeBlood);
        Preconditions.checkBusinessLogique(blood != null, "error.couldn't-find-blood");
        return blood.getBloodGrp() + blood.getRhesus();
    }

    @Transactional(readOnly = true)
    public List<String> findAllGroups() {
        log.debug("***find All Groups***");
        return (findAll(null).stream()
                .map(BloodDTO::getBloodGrp)
                .distinct()
                .toList());
    }

    public BloodDTO addBlood(BloodDTO bloodDTO) {
        log.debug("***add Blood***");
        Blood b = bloodRepository.findBybloodGrpAndRhesus(bloodDTO.getBloodGrp(), bloodDTO.getRhesus());
        Preconditions.checkBusinessLogique(b == null, "error.blood-already-exists");
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
                //Preconditions.checkBusinessLogique(findBloodCodeByType(ch) != null, "error.type-not-found");
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
        log.debug("***update Blood***");
        Blood bloodInDB = bloodRepository.findByCodeBlood(bloodDTO.getCodeBlood());
        bloodDTO.setCodeBlood(bloodInDB.getCodeBlood());
//        bloodDTO.setCreationDate(bloodInDB.getCreationDate());
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

    @Transactional(readOnly = true)
    public List<BloodDTO> getListBloodByCode(List<Integer> codes) {
        log.debug("***get List Blood By Code***");
        Preconditions.checkBusinessLogique(codes != null, "eroor");
        List<BloodDTO> bloodDTOList = new ArrayList<>();
        for (Integer x : codes) {
            bloodDTOList.add(BloodFactory.bloodToBloodDTO(bloodRepository.findByCodeBlood(x)));
        }
        return bloodDTOList;
    }

    public BloodDTO updateStatusBlood(Integer code) {
        log.debug("***update Status Blood***");
        Blood bloodInDB = bloodRepository.findByCodeBlood(code);
        BloodFactory.statusChangeHandler(bloodInDB);
        return BloodFactory.bloodToBloodDTO((bloodRepository.save(bloodInDB)));
    }


}
