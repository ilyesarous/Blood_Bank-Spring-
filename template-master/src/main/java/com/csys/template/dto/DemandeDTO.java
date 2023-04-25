package com.csys.template.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class DemandeDTO {

    private Integer Id;
    private String code;
    private String codeMedecin;
    private String blood;
    private String quantiter;
    private String state;
    private String codeService;
    private String createDateSt;

    private LocalDate createDateLd;
    private String usercreate;
    private String status;
    private String nameMedecin;
    private String nameService;




    public LocalDate getCreateDateLd() {
        return createDateLd;
    }

    public void setCreateDateLd(LocalDate createDateLd) {
        this.createDateLd = createDateLd;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getNameMedecin() {
        return nameMedecin;
    }

    public void setNameMedecin(String nameMedecin) {
        this.nameMedecin = nameMedecin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public String getCreateDate() {
        return createDateSt;
    }

    public void setCreateDate(String createDate) {
        this.createDateSt = createDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeMedecin() {
        return codeMedecin;
    }

    public void setCodeMedecin(String codeMedecin) {
        this.codeMedecin = codeMedecin;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getQuantiter() {
        return quantiter;
    }

    public void setQuantiter(String quantiter) {
        this.quantiter = quantiter;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCodeService() {
        return codeService;
    }

    public void setCodeService(String codeService) {
        this.codeService = codeService;
    }
}
