package com.example.boolentf.androidclient.Classes.person;

public class Race {
    private Double mHealth;
    private Double mSteps;
    private Double mDamage;
    private String mName;

    public void setDamage(Double damage){
        this.mDamage=damage;
    }
    public Double getDamage(){
        return mDamage;
    }
    public void setHealth(Double health){
        this.mHealth=health;
    }
    public Double getHealth(){
        return mHealth;
    }
    public void setSteps(Double steps){
        this.mSteps=steps;
    }
    public Double getSteps(){
        return mSteps;
    }
    public void setName(String name){
        this.mName= name;
    }

}