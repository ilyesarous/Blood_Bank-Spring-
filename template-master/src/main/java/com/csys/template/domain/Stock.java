package com.csys.template.domain;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Table(name = "stock")
@Audited
@AuditTable("stock_AUD")
public class Stock {
    @Column(name = "id", nullable = false)

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "blood")
    private String blood;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "code_donateur", nullable = false)
    private String codedonateur;
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "date_create", nullable = false)
    private LocalDate dateCreate;

    @Column(name = "date_périmé", nullable = false)
    private String dateperime;




    public String getX() {
        return dateperime;
    }

    public void setX(String x) {
        this.dateperime = x;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String patientCode) {
        this.code = patientCode;
    }

    public String getCodedonateur() {
        return codedonateur;
    }

    public void setCodedonateur(String codedonateur) {
        this.codedonateur = codedonateur;
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
