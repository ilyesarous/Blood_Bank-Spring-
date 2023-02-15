package com.csys.template.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BloodDTO {
    private Integer codeBlood;
    private String bloodGrp;
    private String bloodType;
    private String givenTo;
    private String receivedFrom;
    //private List<PatientDTO> patientDTOs;

    public Integer getCodeBlood() {
        return codeBlood;
    }

    public void setCodeBlood(Integer codeBlood) {
        this.codeBlood = codeBlood;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getGivenTo() {
        return givenTo;
    }

    public void setGivenTo(String givenTo) {
        this.givenTo = givenTo;
    }

    public String getReceivedFrom() {
        return receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }

   /* public List<PatientDTO> getPatientDTOs() {
        return patientDTOs;
    }

    public void setPatientDTOs(List<PatientDTO> patients) {
        this.patientDTOs = patients;
    }*/
}
