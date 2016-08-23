package com.example.boolentf.androidclient.Classes.item;

/**
 * Created by BoolenTF on 20.08.2016.
 */
public class Buff /*extends MapObjects*/{

    protected Double mDamage;
    protected Double mLevel;
    protected Double mHealth;
    protected Double mSteps;

    protected String mType;
    protected String mArgument;

    public Buff(String type,String argument){
        mType=type;
        mArgument=argument;
    }

    public void setLevel(Double level){
        this.mLevel= level;
    }
    public Double getLevel(){
        return mLevel;
    }
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
}