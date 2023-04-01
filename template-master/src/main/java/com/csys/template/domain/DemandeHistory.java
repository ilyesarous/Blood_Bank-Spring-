package com.csys.template.domain;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Table(name = "demande_History")
@Audited
@AuditTable("demande_History_AUD")
public class DemandeHistory {


    @Column(name = "id", nullable = false)

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "code_medecin", nullable = false)
    private String codeMedecin;
    @Column(name = "blood", nullable = false)
    private String blood;
    @Column(name = "quantiter", nullable = false)
    private String quantiter;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "code_service", nullable = false)
    private String codeService;
    @Column(name = "Date_create", nullable = false)
    private LocalDate CreateDate;
    @Column(name = "User_Create", nullable = false)
    private String usercreate;
    @Column(name = "Status", nullable = false)
    private Integer status;



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

    public LocalDate getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(LocalDate createDate) {
        CreateDate = createDate;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
