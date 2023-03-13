package com.csys.template.factory;

import com.csys.template.domain.Blood;
import com.csys.template.domain.Patient;
import com.csys.template.dto.PatientDTO;
import com.csys.template.service.BloodService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientFactory {

    private  final BloodService bloodService;

    public PatientFactory(BloodService bloodService) {
        this.bloodService = bloodService;
    }


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
        patientDTO.setBirthDate(patient.getBirthDate());
        patientDTO.setGender(patient.getGender());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        patientDTO.setCreation_date(patient.getCreation_date());
        //patientDTO.setState(patient.getState());
        patientDTO.setBloodCode(patient.getBloodCode().getCodeBlood().toString());

        return patientDTO;
    }

    public static Patient patientDTOToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        LocalDate d = LocalDate.now();
        String ch=patientDTO.getBloodCode();



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
        patient.setBirthDate(patientDTO.getBirthDate());
        String gender= patientDTO.getGender();
        String result="";
        switch (gender) {
            case "10":
                result = "Men";
                break;
            case "20":
                result = "Women";
                break;
            case "30":
                result = "lehou lehou";
                break;
            default:
                result = "Men";
                break;
        }
        patient.setGender(result);
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setCreation_date(d);
        Blood codeBlood = new Blood();
        codeBlood.setCodeBlood(Integer.parseInt(patientDTO.getBloodCode()));
        patient.setBloodCode(codeBlood);
        patient.setCodeBlood(codeBlood.getCodeBlood());
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
