package com.csys.template.dto;

public class MedecinDTO {
    private  Integer Code;
    private String CodeSaisie;
    private String NomInterv;

    public MedecinDTO() {

    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getCodeSaisie() {
        return CodeSaisie;
    }

    public void setCodeSaisie(String codeSaisie) {
        CodeSaisie = codeSaisie;
    }

    public String getNomInterv() {
        return NomInterv;
    }

    public void setNomInterv(String nomInterv) {
        NomInterv = nomInterv;
    }
}
