package com.example.boolentf.androidclient.Classes.person;

import android.content.Context;
import android.graphics.Color;

import com.example.boolentf.androidclient.Classes.item.Card;
import com.example.boolentf.androidclient.Classes.item.Clothes;
import com.example.boolentf.androidclient.R;

import java.util.ArrayList;

public class Player extends Essential{
    private Double mHealth;
    private String mLogin;
    private int mColor;
    private ArrayList<Clothes> mClothes;
    private ArrayList<Card> mHand;
    private Race mRace;
    private ClassPerson mClass;


    public void setHealth(Double health){
        this.mHealth=health;
    }
    public Double getHealth(){
        return mHealth;
    }
    public void setLogin(String login){
        this.mLogin=login;
    }
    public void setColor(String color, Context context){
        int green = context.getResources().getColor(R.color.player_green);
        int orange = context.getResources().getColor(R.color.player_orange);
        int purple = context.getResources().getColor(R.color.player_purple);
        switch(color) {
            case "green":
                mColor=green;
                break;
            case "orange":
                mColor=orange;
                break;
            case "purple":
                mColor=purple;
                break;
        }
    }
    public void getClothes(ArrayList<Clothes> clothes){
        this.mClothes= clothes;
    }
    public int getColor(){
        return mColor;
    }
    public void setRace(Race race){
        this.mRace=race;
    }
    public void setClassPerson(ClassPerson classp){
        this.mClass=classp;
    }

}