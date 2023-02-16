package com.csys.template.enumeration;


public enum StateEnum {
    SOLVED("1"),

    REJECTED("2"),

    PENDING("3");
    private  String name;
    private String user;

    StateEnum(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer intValue(){
        return Integer.parseInt(this.getName());
    }
}
