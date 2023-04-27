package com.csys.template.domain;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "Authentification")
@Audited
@AuditTable("Authentification_AUD")
public class Authentification {

    @Id
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "role")
    private String role;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }
}
