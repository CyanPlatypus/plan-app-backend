package com.plan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserCreateRequestDto {

    private String name;

    private String email;

    private String pass;

    @JsonIgnore
    public static String getNameFieldName(){
        return "name";
    }

    @JsonIgnore
    public static String getEmailFieldName(){
        return "email";
    }

    @JsonIgnore
    public static String getPassFieldName(){
        return "pass";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
