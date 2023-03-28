package com.csys.template.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BloodDTO {
    private Integer codeBlood;
    @NotNull
    private String bloodGrp;
    @NotNull
    private String rhesus;
    private String givenTo;
    private String receivedFrom;
    private String creationDate;
    private String userCreate;
    private Integer active;

    public Integer getCodeBlood() {
        return codeBlood;
    }
    public void setCodeBlood(Integer codeBlood) {
        this.codeBlood = codeBlood;
    }
    public String getBloodGrp() {
        return bloodGrp;
    }
    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }
    public String getRhesus() {
        return rhesus;
    }
    public void setRhesus(String rhesus) {
        this.rhesus = rhesus;
    }
    public String getGivenTo() {
        return givenTo;
    }
    public void setGivenTo(String givenTo) {
        this.givenTo = givenTo;
    }
    public String getReceivedFrom() {
        return receivedFrom;
    }
    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    public String getUserCreate() {
        return userCreate;
    }
    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }
    public Integer getActive() {
        return active;
    }
    public void setActive(Integer active) {
        this.active = active;
    }
}
