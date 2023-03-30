package com.csys.template.dto;

import jakarta.persistence.Column;

public class DemandeDTO {
    private String code;
    private String codeMedecin;
    private String blood;
    private String quantiter;
    private String state;
    private String codeService;
    private String CreateDate;
    private String usercreate;


    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
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
