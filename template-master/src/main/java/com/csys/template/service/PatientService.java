package com.csys.template.service;

import com.csys.template.domain.Counter;
import com.csys.template.domain.Patient;
import com.csys.template.dto.BloodDTO;
import com.csys.template.dto.CounterDTO;
import com.csys.template.dto.PatientDTO;
import com.csys.template.factory.CounterFactory;
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

import static com.csys.template.TemplateApplication.log;

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
        log.debug("*** find All ***");
        List<Patient> patients = patientRepository.findAll(patient);
        Preconditions.checkBusinessLogique(patients!=null,"error patient does not found");
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
    @Transactional(readOnly = true)
    public PatientDTO findByCode(String code){
        Patient patient = patientRepository.findByCode(code);
        Integer bloodcode =patient.getCodeBlood();
        String x=bloodService.findTypeByBloodCode(bloodcode);

        Preconditions.checkBusinessLogique(patient!=null,"patient not existe"+code);
        PatientDTO patientDTO=PatientFactory.patientToPatientDTO(patient);
        patientDTO.setBloodCode(x);
        return patientDTO ;
    }

    public PatientDTO addPatient(PatientDTO patientDTO) {
        log.debug("*** add Patient ***");
        Patient p = patientRepository.findByTypeIdentityAndNumIdentity(patientDTO.getTypeIdentity(), patientDTO.getNumIdentity());
        Preconditions.checkBusinessLogique(p==null, "patient already exists");

        CounterDTO counter = counterService.findCounterByType("patient");
        patientDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);

        String ch ="--";
        String x=bloodService.findBloodCodeByType(ch).toString();
        patientDTO.setBloodCode(x);
        Patient patient = patientRepository.save(PatientFactory.patientDTOToPatient(patientDTO));
        return patientDTO;
    }

    public Patient updatePatient(PatientDTO patientDTO){
        log.debug("*** update Patient ***");
        Patient patientInDB = patientRepository.findByCode(patientDTO.getCode());
        Preconditions.checkBusinessLogique(patientInDB!=null,"error patient does not found");
        patientDTO.setCode(patientInDB.getCode());
        patientDTO.setFirstNameEng(patientInDB.getFirstNameEng());

        patientDTO.setLastNameEng(patientInDB.getLastNameEng());

        patientDTO.setFatherNameEng(patientInDB.getFatherNameEng());

        patientDTO.setGrandFatherNameEng(patientInDB.getGrandFatherNameEng());

        patientDTO.setGender(patientInDB.getGender());
        patientDTO.setBirthDate(patientInDB.getBirthDate());
        LocalDate d=patientInDB.getBirthDate();
        String y=d.toString();
        patientDTO.setBirthDay(y);
        String ch =patientDTO.getBloodCode();
        String x=bloodService.findBloodCodeByType(ch).toString();
        patientDTO.setBloodCode(x);
        return patientRepository.save(PatientFactory.patientDTOToPatient(patientDTO));
    }

    public Patient updateBloodPatient(PatientDTO patientDTO){
        log.debug("*** update blood  Patient ***");
        Patient patientInDB = patientRepository.findByCode(patientDTO.getCode());
        Preconditions.checkBusinessLogique(patientInDB!=null,"error patient does not found");
        patientDTO.setCode(patientInDB.getCode());
        patientDTO.setFirstNameEng(patientInDB.getFirstNameEng());
        patientDTO.setLastNameEng(patientInDB.getLastNameEng());
        patientDTO.setFatherNameEng(patientInDB.getFatherNameEng());
        patientDTO.setGrandFatherNameEng(patientInDB.getGrandFatherNameEng());
        patientDTO.setGender(patientInDB.getGender());
        patientDTO.setBirthDate(patientInDB.getBirthDate());
        patientDTO.setAdress(patientInDB.getAdress());
        patientDTO.setEmail(patientInDB.getEmail());

        LocalDate d=patientInDB.getBirthDate();
        String y=d.toString();
        patientDTO.setBirthDay(y);


        String ch =patientDTO.getBloodCode();
        String x=bloodService.findBloodCodeByType(ch).toString();
        patientDTO.setBloodCode(x);
        return patientRepository.save(PatientFactory.updatepatientDTOToPatient(patientDTO));
    }




}
