package com.example.boolentf.androidclient.Classes.item;

import android.media.Image;

public class Clothes extends Buff{

    protected Image mImage;

    public Clothes(String type, String argument) {
        super(type, argument);
    }

    public Image getImage(){
        return mImage;
    }
    public void setImage(Image image){
        this.mImage= image;
    }
}