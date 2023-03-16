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

    @Column(name = "date_Create", nullable = false)
    private LocalDate dateCreate;
    @Column(name = "fulln_Name", nullable = false)
    private String fullName;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "phone_Number", nullable = false)
    private String phoneNumber;
    @Column(name = "type_Identity", nullable = false)
    private String typeIdentity;
    @Column(name = "Num_Identity")
    private String numIdentity;
    @Column(name = "Sexe")
    private String sexe;
    @Column(name = "adress", nullable = false)
    private String adress;
    @Column(name = "etat", nullable = false)
    private String etat;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String Sexe) {
        sexe = Sexe;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }



}
