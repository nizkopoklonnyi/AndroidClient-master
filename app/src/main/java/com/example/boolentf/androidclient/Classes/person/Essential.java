package com.example.boolentf.androidclient.Classes.person;

import android.content.Context;
import android.view.View;

import com.example.boolentf.androidclient.Classes.item.Buff;

import java.util.ArrayList;

public abstract class Essential extends View {
    /**position x (x,y)*/
    private Double x;

    /**position y (x,y)*/
    private Double y;

    /**level of essential*/
    private Double mLevel;

    /**damage of essential*/
    private Double mDamage;
    private ArrayList<Buff> mBuff;

    public Essential(Context context){
        super(context);
    }

    public void setPositionX(Double x){
        this.x=x;
    }
    public Double getPositionX(){
        return this.x;
    }
    public void setPositionY(Double y){
        this.y=y;
    }
    public Double getPositionY(){
        return this.y;
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
    public void setBuff(ArrayList<Buff> buff){
        this.mBuff=buff;
    }
    public ArrayList<Buff> getBuff(){
        return this.mBuff;
    }

}