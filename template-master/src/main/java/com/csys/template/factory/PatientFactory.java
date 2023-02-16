package com.csys.template.factory;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Patient;
import com.csys.template.dto.PatientDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientFactory {

    public static PatientDTO patientToPatientDTO(Patient patient){
        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setCode(patient.getCode());
        patientDTO.setFirstNameAr(patient.getFirstNameAr());
        patientDTO.setFatherNameAr(patient.getFatherNameAr());
        patientDTO.setGrandFatherNameAr(patient.getGrandFatherNameAr());
        patientDTO.setLastNameAr(patient.getLastNameAr());
        patientDTO.setFullNameAr();

        patientDTO.setFirstNameEng(patient.getFirstNameEng());
        patientDTO.setFatherNameEng(patient.getFatherNameEng());
        patientDTO.setGrandFatherNameEng(patient.getGrandFatherNameEng());
        patientDTO.setLastNameEng(patient.getLastNameEng());
        patientDTO.setFullNameEng();

        patientDTO.setAdress(patient.getAdress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setBirthDate(patient.getBirthDate().getTime());
        patientDTO.setGender(patient.getGender());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        patientDTO.setCreation_date(patient.getCreation_date().getTime());
        //patientDTO.setState(patient.getState());
        patientDTO.setBloodCode(patient.getBloodCode().getCodeBlood());

        return patientDTO;
    }

    public static Patient patientDTOToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();

        patient.setCode(patientDTO.getCode());
        patient.setFirstNameAr(patientDTO.getFirstNameAr());
        patient.setFatherNameAr(patientDTO.getFatherNameAr());
        patient.setGrandFatherNameAr(patientDTO.getGrandFatherNameAr());
        patient.setLastNameAr(patientDTO.getLastNameAr());
        patient.setFullNameAr();

        patient.setFirstNameEng(patientDTO.getFirstNameEng());
        patient.setFatherNameEng(patientDTO.getFatherNameEng());
        patient.setGrandFatherNameEng(patientDTO.getGrandFatherNameEng());
        patient.setLastNameEng(patientDTO.getLastNameEng());
        patient.setFullNameEng();

        patient.setAdress(patientDTO.getAdress());
        patient.setEmail(patientDTO.getEmail());
        patient.setBirthDate(new Date(patientDTO.getBirthDate()));
        patient.setGender(patientDTO.getGender());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setCreation_date(new Date(patientDTO.getCreation_date()));
        //patient.setState(patientDTO.getState());

        Blood codeBlood = new Blood();
        codeBlood.setCodeBlood(patientDTO.getBloodCode());
        patient.setBloodCode(codeBlood);
        return patient;
    }

    public static List<PatientDTO> patientsToPatientsDTO(List<Patient> patients){
        List<PatientDTO> patientDTOS = new ArrayList<PatientDTO>();
        for(Patient patient : patients){
            patientDTOS.add(patientToPatientDTO(patient));
        }
        return patientDTOS;
    }

    public static List<Patient> patientsDTOToPatients(List<PatientDTO> patientsDTO){
        List<Patient> patients = new ArrayList<Patient>();
        for(PatientDTO patient : patientsDTO){
            patients.add(patientDTOToPatient(patient));
        }
        return patients;
    }


}
