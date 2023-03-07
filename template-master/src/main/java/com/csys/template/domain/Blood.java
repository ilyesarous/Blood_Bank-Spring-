package com.csys.template.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;


@Entity
@Table(name = "blood")
@Audited
@AuditTable("blood_AUD")
public class Blood {
    @Column(name = "blood_code", nullable = false)
    @JsonManagedReference
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer codeBlood;

    @Column(name = "blood_group", nullable = false)
    private String bloodGrp;
    @Column(name = "blood_type", nullable = false)
    private String bloodType;
    @Column(name = "given_to", nullable = true)
    private String givenTo;
    @Column(name = "receive_from", nullable = true)
    private String receivedFrom;
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "active", nullable = false)
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

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
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
