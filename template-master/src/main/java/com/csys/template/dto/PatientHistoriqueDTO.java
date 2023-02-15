package com.csys.template.dto;

public class PatientHistoriqueDTO {
    private Integer code;
    private String codePatient;
    private Integer state;
    private String observation;
    private String userCreate;
    private Long dateCreate;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(String codePatient) {
        this.codePatient = codePatient;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Long dateCreate) {
        this.dateCreate = dateCreate;
    }
}
