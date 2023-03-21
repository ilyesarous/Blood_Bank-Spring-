package com.csys.template.domain;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "donation_history")
@Audited
@AuditTable("donation_history_AUD")
public class DonationsHistory {

    @Column(name = "id", nullable = false)

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "code_patient", nullable = false)
    private String patientCode;
    @Column(name = "state", nullable = false)
    private Integer state;
    @Column(name = "observation", nullable = false)
    private String observation;
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "date_create", nullable = false)
    private LocalDate dateCreate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodePatient() {
        return patientCode;
    }

    public void setCodePatient(String patientCode) {
        this.patientCode = patientCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
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
