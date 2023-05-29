package com.csys.template.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;


import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class DonationDTO {

    private Integer id;
    private String code;
    private String codePatient;
    private String fullName;
    private Integer diastolicPressure;
    private Integer systolicPressure;
    private String sexe;
    private String phoneNumber;
    private String adress;
    private String datecreationSt;
    private LocalDate datecreationLd;
    private String userCreate;
    private String etat;
    private String Blood;
    private String observation;



    public LocalDate getDatecreationLd() {
        return datecreationLd;
    }

    public void setDatecreationLd(LocalDate datecreationLd) {
        this.datecreationLd = datecreationLd;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(String codePatient) {
        this.codePatient = codePatient;
    }

    public Integer getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(Integer diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public Integer getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(Integer systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getDate_creation() {
        return datecreationSt;
    }

    public void setDate_creation(String date_creation) {
        this.datecreationSt = date_creation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }
}
