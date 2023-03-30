package com.csys.template.dto;

import jakarta.persistence.Basic;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ServiceDTO {
    private Integer code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String designationAr;
    @Size(max = 200)
    private String designation;
    @NotNull
    private boolean actif;

    private Integer codeCostCentre;

    public ServiceDTO() {
    }

    public Integer getCodeCostCentre() {
        return codeCostCentre;
    }

    public void setCodeCostCentre(Integer codeCostCentre) {
        this.codeCostCentre = codeCostCentre;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
