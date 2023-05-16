package com.csys.template.domain;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Table(name = "ReceiptAfterDonation")
@Audited
@AuditTable("ReceiptAfterDonation_AUD")
public class ReceiptAfterDonation {

    @Column(name = "id", nullable = false)

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "code_Patient", nullable = false)

    private String codepatient;
    @Column(name = "date_create", nullable = false)
    private LocalDate dateCreate;
    @Column(name = "date_peremption", nullable = false)
    private String dateperime;
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "etat", nullable = false)
    private Integer etat;
    @Column(name = "blood", nullable = false)
    private Integer blood;


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

    public String getCodepatient() {
        return codepatient;
    }

    public void setCodepatient(String codepatient) {
        this.codepatient = codepatient;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateperime() {
        return dateperime;
    }

    public void setDateperime(String dateperime) {
        this.dateperime = dateperime;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }
}
