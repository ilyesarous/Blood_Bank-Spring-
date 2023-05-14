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
        LocalDate d=patient.getCreation_date();
        LocalDate birdh=patient.getBirthDate();

        String x=d.toString();
        String b=birdh.toString();

        patientDTO.setCode(patient.getCode());

        patientDTO.setFirstNameEng(patient.getFirstNameEng());
        patientDTO.setFatherNameEng(patient.getFatherNameEng());
        patientDTO.setGrandFatherNameEng(patient.getGrandFatherNameEng());
        patientDTO.setLastNameEng(patient.getLastNameEng());
        patientDTO.setFullNameEng();

        patientDTO.setAdress(patient.getAdress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setGender(patient.getGender());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());

        patientDTO.setTypeIdentity(patient.getTypeIdentity());
        patientDTO.setNumIdentity(patient.getNumIdentity());
        patientDTO.setCreation_date(x);
        patientDTO.setBirthDay(b);
        patientDTO.setCreationdateLd(patient.getCreation_date());


        return patientDTO;
    }

    public static Patient patientDTOToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        LocalDate d = LocalDate.now();

        patient.setCode(patientDTO.getCode());

        patient.setFirstNameEng(patientDTO.getFirstNameEng());
        patient.setFatherNameEng(patientDTO.getFatherNameEng());
        patient.setGrandFatherNameEng(patientDTO.getGrandFatherNameEng());
        patient.setLastNameEng(patientDTO.getLastNameEng());
        patient.setFullNameEng();

        patient.setAdress(patientDTO.getAdress());
        patient.setEmail(patientDTO.getEmail());
        patient.setBirthDate(d);
        patient.setGender(patientDTO.getGender());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setTypeIdentity(patientDTO.getTypeIdentity());
        patient.setNumIdentity(patientDTO.getNumIdentity());
        patient.setCreation_date(d);
        Integer bloodcode=Integer.parseInt(patientDTO.getBloodCode());
        patient.setCodeBlood(bloodcode);

        return patient;
    }
    public static Patient updatepatientDTOToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        LocalDate d = LocalDate.now();

        patient.setCode(patientDTO.getCode());

        patient.setFirstNameEng(patientDTO.getFirstNameEng());
        patient.setFatherNameEng(patientDTO.getFatherNameEng());
        patient.setGrandFatherNameEng(patientDTO.getGrandFatherNameEng());
        patient.setLastNameEng(patientDTO.getLastNameEng());
        patient.setFullNameEng();

        patient.setAdress(patientDTO.getAdress());
        patient.setEmail(patientDTO.getEmail());
        patient.setBirthDate(d);
        patient.setGender(patientDTO.getGender());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setCreation_date(d);
        patient.setTypeIdentity(patientDTO.getTypeIdentity());
        patient.setNumIdentity(patientDTO.getNumIdentity());

        Integer blood= Integer.parseInt(patientDTO.getBloodCode());
        patient.setCodeBlood(blood);

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
