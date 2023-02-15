package com.csys.template.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {
    private Integer id;
    private String code;
    private String firstNameAr;
    private String lastNameAr;
    private String fatherNameAr;
    private String grandFatherNameAr;
    private String fullNameAr;
    private String firstNameEng;
    private String lastNameEng;
    private String fatherNameEng;
    private String grandFatherNameEng;
    private String fullNameEng;
    private Long birthDate;
    private String gender;
    private String phoneNumber;
    private String adress;
    private String email;
    private Long creation_date;
    private String State;
    private Integer bloodCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLastNameAr() {
        return lastNameAr;
    }

    public void setLastNameAr(String lastNameAr) {
        this.lastNameAr = lastNameAr;
    }

    public String getFirstNameAr() {
        return firstNameAr;
    }

    public void setFirstNameAr(String firstNameAr) {
        this.firstNameAr = firstNameAr;
    }

    public String getFatherNameAr() {
        return fatherNameAr;
    }

    public void setFatherNameAr(String fatherNameAr) {
        this.fatherNameAr = fatherNameAr;
    }

    public String getGrandFatherNameAr() {
        return grandFatherNameAr;
    }

    public void setGrandFatherNameAr(String grandFatherNameAr) {
        this.grandFatherNameAr = grandFatherNameAr;
    }

    public String getFullNameAr() {
        return fullNameAr;
    }

    public void setFullNameAr() {
        this.fullNameAr = this.getFirstNameAr()+this.getFatherNameAr()+this.getGrandFatherNameAr()+getLastNameAr();
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
        this.fullNameEng = this.getFirstNameEng()+this.getFatherNameEng()+this.getGrandFatherNameEng()+getLastNameEng();
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
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

    public Integer getBloodCode() {
        return bloodCode;
    }

    public void setBloodCode(Integer bloodCode) {
        this.bloodCode = bloodCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Long creation_date) {
        this.creation_date = creation_date;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
