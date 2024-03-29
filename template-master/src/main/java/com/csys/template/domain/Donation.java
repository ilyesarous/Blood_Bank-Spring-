package com.csys.template.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "donation")
@Audited
@AuditTable("Donation_AUD")
public class Donation implements Serializable {

    @Column(name = "code", nullable = false)
    @Id
    private String code;
    @Column(name = "code_Patient", nullable = false)

    private String codepatient;
    @Column(name = "date_create", nullable = false)
    private LocalDate dateCreate;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "diastolic_blood_pressure", nullable = false)
    private Integer diastolicPressure;
    @Column(name = "systolic_blood_pressure", nullable = false)
    private Integer systolicPressure;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "sexe")
    private String sexe;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "etat", nullable = false)
    private Integer etat;
    @Column(name = "blood", nullable = false)
    private String Blood;
    @Column(name = "observation", nullable = false)
    private String observation;


    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCodepatient() {
        return codepatient;
    }

    public void setCodepatient(String codepatient) {
        this.codepatient = codepatient;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getSexe() {
        return sexe;
    }

    public void setSexe(String Sexe) {
        sexe = Sexe;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }
}
