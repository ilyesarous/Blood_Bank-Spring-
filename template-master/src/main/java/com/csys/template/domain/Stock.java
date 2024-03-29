package com.csys.template.domain;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "stock")
@Audited
@AuditTable("stock_AUD")
public class Stock {
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
    @Column(name = "date_peremption", nullable = false)
    private String dateperime;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "request", nullable = false)
    private String resquest;

//    @Version
    @Column(name = "version", nullable = false)
    private Integer version;




    public String getResquest() {
        return resquest;
    }

    public void setResquest(String resquest) {
        this.resquest = resquest;
    }

    public Integer getQuantite() {
        return quantity;
    }

    public void setQuantite(Integer quantite) {
        this.quantity = quantite;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDateperime() {
        return dateperime;
    }

    public void setDateperime(String dateperime) {
        this.dateperime = dateperime;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlood() {
        return bloodCode;
    }

    public void setBlood(Integer blood) {
        this.bloodCode = blood;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String patientCode) {
        this.code = patientCode;
    }

    public String getCodedonateur() {
        return codedonation;
    }

    public void setCodedonateur(String codedonateur) {
        this.codedonation = codedonateur;
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

}
