package com.csys.template.service;

import com.csys.template.domain.Patient;
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
        List<PatientDTO> patientDTOS= PatientFactory.patientsToPatientsDTO(patientRepository.findAll());
      //  List<Integer> bloodcode = patientDTOS.stream().filter( x-> x.getBloodCode()!= null)
        // .map(x-> x.getBloodCode())
        //.distinct().collect(Collectors.toList());

        //for (Integer blood: bloodcode) {
          //  String ch=bloodService.findTypeByBloodCode(blood);


        //}


        //find all by blood code in
        return patientDTOS;
    }
    @Transactional(readOnly = true)
    public PatientDTO findPatientByCode(String code){
        Patient patient = patientRepository.findByCode(code);
        return PatientFactory.patientToPatientDTO(patient);
    }

    public PatientDTO addPatient(PatientDTO patientDTO) {
        CounterDTO counter = counterService.findCounterByType("patient");
        patientDTO.setCode(counter.getPrefix()+counter.getSuffix());
        counter.setSuffix(counter.getSuffix()+1);
        counterService.updateCounter(counter);
        Patient patient = PatientFactory.patientDTOToPatient(patientDTO);
        patient = patientRepository.save(patient);
        return patientDTO;
    }

    public Patient updatePatient(PatientDTO patientDTO){
        Patient patientInDB = patientRepository.findByCode(patientDTO.getCode());
        Preconditions.checkArgument (patientInDB != null, "Patient has been updated");
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
        patientDTO.setCreation_date(patientInDB.getCreation_date());
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
