package com.csys.template.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "donation_history")
public class PatientHistorique {

    @Column(name = "code", nullable = false)
    @Id
    private Integer code;
    @Column(name = "code_patient", nullable = false)
    private String codePatient;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "observation", nullable = false)
    private String observation;
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "date_create", nullable = false)
    private Date dateCreate;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}
