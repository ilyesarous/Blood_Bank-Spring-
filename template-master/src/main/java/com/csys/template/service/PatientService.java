package com.csys.template.service;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Patient;
import com.csys.template.dto.BloodDTO;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.PatientDTO;
import com.csys.template.factory.PatientFactory;
import com.csys.template.repository.PatientRepository;
import com.csys.template.util.Preconditions;
import org.springframework.data.jpa.domain.Specification;
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
    public List<PatientDTO> findAll(Specification<Patient> patient) {
        List<Patient> patients = patientRepository.findAll(patient);
        Preconditions.checkBusinessLogique(patients!=null,"error patient not exist");
        List<Integer> bloodCodes = patients.stream()
                .map(Patient::getCodeBlood)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(bloodCodes);
        List<BloodDTO> bloodDTOs = bloodService.getListBloodByCode(bloodCodes);
        List<PatientDTO> patientDTOS = new ArrayList<>();
        patients.forEach(p -> {
            PatientDTO patientDTO1 = PatientFactory.patientToPatientDTO(p);
            Optional<BloodDTO> bloodDTOOptional = bloodDTOs.stream()
                    .filter(b -> b.getCodeBlood().compareTo(p.getCodeBlood()) == 0)
                    .findFirst();
            if (bloodDTOOptional.isPresent()) {
                patientDTO1.setBloodCode(bloodDTOOptional.get().getBloodGrp()+bloodDTOOptional.get().getRhesus());
            }
            patientDTOS.add(patientDTO1);
        });

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
        Preconditions.checkBusinessLogique(patientInDB!=null,"error patient not exist");
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




}
