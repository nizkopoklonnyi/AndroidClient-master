package com.example.boolentf.androidclient.Classes.DATA;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameForTransaction {
    private String name;
    private String password;
    private boolean open;

    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public boolean getOpen(){
        return open;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setOpen(boolean open){
        this.open=open;
    }
}
