package com.csys.template.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class BonReceptionDTO {
    private Integer id;

    private String bloodCode;
    private String code;
    private String codedonation;
    private String userCreate;
    private String dateCreate;
    private String dateperime;
    private Integer quantity;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBloodCode() {
        return bloodCode;
    }

    public void setBloodCode(String bloodCode) {
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

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
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
