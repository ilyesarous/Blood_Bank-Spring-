package com.csys.template.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "state")

public class State {

    @Column(name = "code", nullable = false)
    @JsonManagedReference
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer code;
    @Column(name = "name", nullable = false)

    private String name;
    @Column(name = "user", nullable = false)

    private String user;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
