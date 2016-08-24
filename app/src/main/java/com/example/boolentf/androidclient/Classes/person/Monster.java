package com.example.boolentf.androidclient.Classes.person;

import android.content.Context;
import android.media.Image;

public class Monster extends Essential{
    private String mName;
    private Image mImage;
    private Double mMinLevel;
    public Monster(Context context){
        super(context);
    }

    public void setName(String name){
        this.mName=name;
    }
    public String getName(){
        return this.mName;
    }
    public Image getImage(){
        return mImage;
    }
    public void setImage(Image image){
        this.mImage= image;
    }
    public void setMinLevel(Double minLevel){
        this.mMinLevel=minLevel;
    }
    public Double getMinLevel(){
        return this.mMinLevel;
    }
}
