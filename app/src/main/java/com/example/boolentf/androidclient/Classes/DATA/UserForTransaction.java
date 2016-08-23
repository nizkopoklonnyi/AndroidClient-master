package com.example.boolentf.androidclient.Classes.DATA;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserForTransaction {
    private String login;
    private String password;

    public String getLogin(){return login; }
    public String getPassword(){
        return password;
    }

    public void setLogin(String login){
        this.login=login;
    }
    public void setPassword(String password){
        this.password=password;
    }
}
