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

@Service
@Transactional
public class BloodService {

    private final BloodRepository bloodRepository;

    public BloodService(BloodRepository bloodRepository) {
        this.bloodRepository = bloodRepository;
    }

    @Transactional(readOnly = true)
    public List<BloodDTO> findAll(Specification<Blood> specification) {
        List<Blood> bloods = bloodRepository.findAll(specification);
        Preconditions.checkBusinessLogique(bloods != null, "error.couldn't-find-bloods");
        for (Blood b : bloods) {
            if (!Objects.equals(b.getGivenTo(), "-")) {
                String[] given = b.getGivenTo().split(",");
                StringBuilder s = new StringBuilder(" ");
                for (String ch : given) {
                    if (!Objects.equals(ch, "")) {
                        s.append(findTypeByBloodCode(Integer.parseInt(ch))).append(",");
                    }
                }
                b.setGivenTo(s.substring(0, s.length() - 1).trim());
            }
            if (!Objects.equals(b.getReceivedFrom(), "-")) {
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


    public Blood findByGroupAndRhesus(String group, String rhesus) {
        List<Blood> blood = bloodRepository.findBybloodGrp(group);
        Preconditions.checkBusinessLogique(blood != null, "error.couldn't-find-blood");
        for (Blood b : blood) {
            if (Objects.equals(b.getRhesus(), rhesus))
                return b;
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Integer findBloodCodeByType(String ch) {
        String rhesus;
        rhesus = ch.substring(ch.length() - 1);
        String group = ch.substring(0, ch.length() - 1);
        System.out.println("rhesus: " + rhesus);
        System.out.println("group: " + group);
        if (findByGroupAndRhesus(group, rhesus) != null) {
            return findByGroupAndRhesus(group, rhesus).getCodeBlood();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Blood findBloodByCode(Integer code) {
        return bloodRepository.findByCodeBlood(code);
    }

    @Transactional(readOnly = true)
    public String findTypeByBloodCode(Integer codeBlood) {
        Blood blood = bloodRepository.findByCodeBlood(codeBlood);
        Preconditions.checkBusinessLogique(blood != null, "error.couldn't-find-blood");
        return blood.getBloodGrp() + blood.getRhesus();
    }

    @Transactional(readOnly = true)
    public List<String> findAllTypes() {
        return (findAll(null).stream()
                .map(x -> x.getBloodGrp() + x.getRhesus())
                .distinct()
                .toList());
    }

    @Transactional(readOnly = true)
    public List<String> findAllGroups() {
        return (findAll(null).stream()
                .map(BloodDTO::getBloodGrp)
                .distinct()
                .toList());
    }


    public BloodDTO addBlood(BloodDTO bloodDTO) {
        Blood b = findByGroupAndRhesus(bloodDTO.getBloodGrp(), bloodDTO.getRhesus());
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
        Preconditions.checkBusinessLogique(codes != null, "eroor");
        List<BloodDTO> bloodDTOList = new ArrayList<>();
        for (Integer x : codes) {
            bloodDTOList.add(BloodFactory.bloodToBloodDTO(bloodRepository.findByCodeBlood(x)));
        }
        return bloodDTOList;
    }

    public BloodDTO updateStatusBlood(Integer code) {
        Blood bloodInDB = bloodRepository.findByCodeBlood(code);
        BloodFactory.statusChangeHandler(bloodInDB);
        return BloodFactory.bloodToBloodDTO((bloodRepository.save(bloodInDB)));
    }


}
