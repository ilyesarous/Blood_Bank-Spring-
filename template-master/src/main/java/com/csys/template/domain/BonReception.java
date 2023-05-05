package com.csys.template.domain;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Table(name = "Bon_Reception")
@Audited
@AuditTable("BonReception_AUD")
public class BonReception {
    @Column(name = "id", nullable = false)

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "blood_code")
    private Integer bloodCode;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "code_donation", nullable = false)
    private String codedonation;
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "date_create", nullable = false)
    private LocalDate dateCreate;
    @Column(name = "date_per", nullable = false)
    private String dateperime;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBloodCode() {
        return bloodCode;
    }

    public void setBloodCode(Integer bloodCode) {
        this.bloodCode = bloodCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodedonation() {
        return codedonation;
    }

    public void setCodedonation(String codedonation) {
        this.codedonation = codedonation;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
