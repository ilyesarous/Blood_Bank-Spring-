package com.csys.template.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {
    private Integer id;
    private String code;
//    @NotNull
    private String firstNameEng;
    private String lastNameEng;
    private String fatherNameEng;
    private String grandFatherNameEng;
    private String fullNameEng;
    private LocalDate birthDate;
    private String birthDay;
    private String typeIdentity;
    private String numIdentity;
    private String gender;
    private String phoneNumber;
    private String adress;
    private String email;
    private String creationdateSt;
    private LocalDate creationdateLd;
    private String bloodCode;




    public String getTypeIdentity() {
        return typeIdentity;
    }

    public void setTypeIdentity(String typeIdentity) {
        this.typeIdentity = typeIdentity;
    }

    public String getNumIdentity() {
        return numIdentity;
    }

    public void setNumIdentity(String numIdentity) {
        this.numIdentity = numIdentity;
    }

    public LocalDate getCreationdateLd() {
        return creationdateLd;
    }

    public void setCreationdateLd(LocalDate creationdateLd) {
        this.creationdateLd = creationdateLd;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getLastNameEng() {
        return lastNameEng;
    }

    public void setLastNameEng(String lastNameEng) {
        this.lastNameEng = lastNameEng;
    }

    public String getFirstNameEng() {
        return firstNameEng;
    }

    public void setFirstNameEng(String firstNameEng) {
        this.firstNameEng = firstNameEng;
    }

    public String getFatherNameEng() {
        return fatherNameEng;
    }

    public void setFatherNameEng(String fatherNameEng) {
        this.fatherNameEng = fatherNameEng;
    }

    public String getGrandFatherNameEng() {
        return grandFatherNameEng;
    }

    public void setGrandFatherNameEng(String grandFatherNameEng) {
        this.grandFatherNameEng = grandFatherNameEng;
    }

    public String getFullNameEng() {
        return fullNameEng;
    }

    public void setFullNameEng() {
        this.fullNameEng = this.getFirstNameEng()+" "+this.getFatherNameEng()+" "+this.getGrandFatherNameEng()+" "+getLastNameEng();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodCode() {
        return bloodCode;
    }

    public void setBloodCode(String bloodCode) {
        this.bloodCode = bloodCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreation_date() {
        return creationdateSt;
    }

    public void setCreation_date(String creation_date) {
        this.creationdateSt = creation_date;
    }

}
