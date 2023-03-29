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
    private Integer age;
    private String sexe;
    private String typeIdentity;
    private String numIdentity;
    private String phoneNumber;
    private String adress;
    private String date_creation;
    private String userCreate;
    private String etat;
    private String Blood;
    private String observation;


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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTypeIdentity() {
        return typeIdentity;
    }

    public void setTypeIdentity(String typeIdentity) {
        this.typeIdentity = typeIdentity;
    }

    public String getNumIdentity() {
        return numIdentity;
    }

    public void setNumIdentity(String NumIdentity) {
        numIdentity = NumIdentity;
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
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
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
