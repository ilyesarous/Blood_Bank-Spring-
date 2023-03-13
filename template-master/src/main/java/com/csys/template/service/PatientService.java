package com.csys.template.service;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Patient;
import com.csys.template.dto.BloodDTO;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.PatientDTO;
import com.csys.template.factory.PatientFactory;
import com.csys.template.repository.PatientRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    private final CounterService counterService ;
    private final BloodService bloodService;
    public PatientService(PatientRepository patientRepository, CounterService counterService, BloodService bloodService) {
        this.patientRepository = patientRepository;
        this.counterService = counterService;
        this.bloodService = bloodService;
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> findAll(){
//        List<Patient> patient= patientRepository.findAll();
//        List<Integer> bloodCodes = patient.stream()
//                .map(x -> x.getCodeBlood())
//                .distinct()
//                .collect(Collectors.toList());
//        List<BloodDTO> bloodDTOs = bloodService.findByCodeIn(bloodCodes);
//        List<PatientDTO> patientDTOS = new ArrayList<>();
//        patient.forEach(p ->{
//            PatientDTO patientDTO1 = PatientFactory.patientToPatientDTO(p);
//            Optional<BloodDTO> bloodDTOOptional = bloodDTOs.stream()
//                    .filter(b -> b.getCodeBlood().compareTo(p.getCodeBlood()) == 0)
//                    .findFirst();
//            if(bloodDTOOptional.isPresent()){
//                patientDTO1.setBloodCode(bloodDTOOptional.get().getBloodType());
//            }
//            patientDTOS.add(patientDTO1);
//        });
//
//        return patientDTOS;
        List<Patient> patient= patientRepository.findAll();
          List<Patient> patients = patient.stream().filter( x-> x.getBloodCode()!= null)
        .distinct().collect(Collectors.toList());

          PatientDTO patientDTO1;
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient p : patient) {
            //for (PatientDTO pa : patientDTOS) {
                patientDTO1 = PatientFactory.patientToPatientDTO(p);
                Integer x = p.getBloodCode().getCodeBlood();
                String ch = bloodService.findTypeByBloodCode(x);
                patientDTO1.setBloodCode(ch);
               patientDTOS.add(patientDTO1);
            //}
        }

        return patientDTOS;
    }
    @Transactional(readOnly = true)
    public PatientDTO findPatientByCode(String code){
        Patient patient = patientRepository.findByCode(code);
        return PatientFactory.patientToPatientDTO(patient);
    }
    @Transactional(readOnly = true)

    public List<PatientDTO>findPatientByNumTel(String Numtel){
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (PatientDTO patientDTO : findAll()){
            if (patientDTO.getPhoneNumber().equals(Numtel))
                patientDTOS.add(patientDTO);
        }
        return patientDTOS;
    }

    @Transactional(readOnly = true)
    public List<PatientDTO>findPatientByLastNamear(String LastNamear){
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (PatientDTO patientDTO : findAll()){
            if (patientDTO.getLastNameAr().equals(LastNamear))
                patientDTOS.add(patientDTO);
        }
        return patientDTOS;
    }
    public PatientDTO addPatient(PatientDTO patientDTO) {
        CounterDTO counter = counterService.findCounterByType("patient");
        patientDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        String ch =patientDTO.getBloodCode();
        String x=bloodService.findBloodCodeByType(ch).toString();
        patientDTO.setBloodCode(x);
        Patient patient = PatientFactory.patientDTOToPatient(patientDTO);
        patient = patientRepository.save(patient);
        return patientDTO;
    }

    public Patient updatePatient(PatientDTO patientDTO){
        Patient patientInDB = patientRepository.findByCode(patientDTO.getCode());

        patientDTO.setCode(patientInDB.getCode());
        patientDTO.setFirstNameEng(patientInDB.getFirstNameEng());
        patientDTO.setFirstNameAr(patientInDB.getFirstNameAr());
        patientDTO.setLastNameEng(patientInDB.getLastNameEng());
        patientDTO.setLastNameAr(patientInDB.getLastNameAr());
        patientDTO.setFatherNameEng(patientInDB.getFatherNameEng());
        patientDTO.setFatherNameAr(patientInDB.getFatherNameAr());
        patientDTO.setGrandFatherNameEng(patientInDB.getGrandFatherNameEng());
        patientDTO.setGrandFatherNameAr(patientInDB.getGrandFatherNameAr());
        patientDTO.setGender(patientInDB.getGender());
        patientDTO.setBirthDate(patientInDB.getBirthDate());

        String ch =patientDTO.getBloodCode();
        String x=bloodService.findBloodCodeByType(ch).toString();
        patientDTO.setBloodCode(x);
        return patientRepository.save(PatientFactory.patientDTOToPatient(patientDTO));
    }

    public List<PatientDTO>findPatientsWithBloodCode(Integer bloodCode){
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (PatientDTO patientDTO : findAll()){
            if (patientDTO.getBloodCode().equals(bloodCode))
                patientDTOS.add(patientDTO);
        }
        return patientDTOS;
    }


}
