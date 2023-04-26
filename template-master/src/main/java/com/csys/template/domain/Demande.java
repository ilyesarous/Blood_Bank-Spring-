package com.csys.template.domain;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Table(name = "demande")
@Audited
@AuditTable("demande_AUD")
public class Demande {
    @Column(name = "code", nullable = false)
    @Id
    private String code;
    @Column(name = "doctor_code", nullable = false)
    private Integer codeMedecin;
    @Column(name = "doctor_name")
    private String nameMedecin;
    @Column(name = "service_name")
    private String nameService;
    @Column(name = "blood", nullable = false)
    private Integer blood;
    @Column(name = "quantity", nullable = false)
    private String quantity;
    @Column(name = "state", nullable = false)
    private Integer state;
    @Column(name = "service_code", nullable = false)
    private Integer serviceCode;
    @Column(name = "create_Date", nullable = false)
    private LocalDate createDate;
    @Column(name = "User_Create", nullable = false)
    private String usercreate;
    @Column(name = "Status", nullable = false)
    private Integer status;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCodeMedecin() {
        return codeMedecin;
    }

    public void setCodeMedecin(Integer codeMedecin) {
        this.codeMedecin = codeMedecin;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public String getQuantiter() {
        return quantity;
    }

    public void setQuantiter(String quantiter) {
        this.quantity = quantiter;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCodeService() {
        return serviceCode;
    }

    public void setCodeService(Integer codeService) {
        this.serviceCode = codeService;
    }
}
