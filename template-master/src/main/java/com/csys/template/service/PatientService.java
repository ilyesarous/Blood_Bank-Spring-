package com.csys.template.service;

import com.csys.template.domain.Patient;
import com.csys.template.dto.PatientDTO;
import com.csys.template.factory.PatientFactory;
import com.csys.template.repository.PatientRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> findAll(){
        return PatientFactory.patientsToPatientsDTO(patientRepository.findAll());
    }
    @Transactional(readOnly = true)
    public PatientDTO findPatientByCode(String code){
        Patient patient = patientRepository.findByCode(code);
        return PatientFactory.patientToPatientDTO(patient);
    }

    public Patient addPatient(PatientDTO articleDTO) {
        Patient patient = PatientFactory.patientDTOToPatient(articleDTO);
        patient = patientRepository.save(patient);
        return patient;
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
        patientDTO.setBirthDate(patientInDB.getBirthDate().getTime());
        patientDTO.setCreation_date(patientInDB.getCreation_date().getTime());
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
