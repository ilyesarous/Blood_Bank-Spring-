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
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "date_create", nullable = false)
    private LocalDate dateCreate;
    @Column(name = "date_périmé")
    private LocalDate dateperime;
    @Column(name = "x", nullable = false)
    private String x;




    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
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

    public LocalDate getDateperime() {
        return dateperime;
    }

    public void setDateperime(LocalDate dateperime) {
        this.dateperime = dateperime;
    }
}
