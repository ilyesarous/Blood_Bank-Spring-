package com.csys.template.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
@Audited
@AuditTable("patient_AUD")
public class Patient implements Serializable {

    @Column(name = "code", nullable = false)
    @Id
    private String code;
    @Column(name = "last_name_ar", nullable = false)
    private String lastNameAr;
    @Column(name = "first_name_ar", nullable = false)
    private String firstNameAr;
    @Column(name = "father_name_ar", nullable = false)
    private String fatherNameAr;
    @Column(name = "grand_father_name_ar", nullable = false)
    private String grandFatherNameAr;
    @Column(name = "full_name_ar", nullable = false)
    private String fullNameAr;
    @Column(name = "last_name_eng", nullable = false)
    private String lastNameEng;
    @Column(name = "first_name_eng", nullable = false)
    private String firstNameEng;
    @Column(name = "father_name_eng", nullable = false)
    private String fatherNameEng;
    @Column(name = "grand_father_name_eng", nullable = false)
    private String grandFatherNameEng;
    @Column(name = "full_name_eng", nullable = false)
    private String fullNameEng;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "creation_date", nullable = false)
    private LocalDate creation_date;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_blood")
    private Blood bloodCode;
    //insertable=false, updatable =false
    @Column(name = "blood")
    private Integer codeBlood;

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
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Blood getBloodCode() {
        return bloodCode;
    }

    public void setBloodCode(Blood bloodCode) {
        this.bloodCode = bloodCode;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public Integer getCodeBlood() {
        return codeBlood;
    }

    public void setCodeBlood(Integer codeBlood) {
        this.codeBlood = codeBlood;
    }

}