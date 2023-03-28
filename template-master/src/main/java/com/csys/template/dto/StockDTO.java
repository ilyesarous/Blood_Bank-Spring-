package com.csys.template.dto;

import java.time.LocalDate;

public class StockDTO {
    private Integer id;

    private String blood;

    private String code;

    private String userCreate;

    private LocalDate dateCreate;
    private LocalDate dateperime;
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
