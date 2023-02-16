package com.csys.template.dto;



public class StateDTO {

    private Integer code;
    private String name;
    private String user;


    public Integer getId() {
        return code;
    }

    public void setId(Integer id) {
        this.code = id;
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
