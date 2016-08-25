package com.example.boolentf.androidclient.Classes.person;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import com.example.boolentf.androidclient.Classes.item.Card;
import com.example.boolentf.androidclient.Classes.item.Clothes;
import com.example.boolentf.androidclient.R;

import java.util.ArrayList;

public class Player extends Essential implements View.OnClickListener{
    private Double mHealth;
    private String mLogin;
    private int mColor;
    private ArrayList<Clothes> mClothes;
    private ArrayList<Card> mHand;
    private Race mRace;
    private ClassPerson mClass;
    private Context mContext;

    private int mLenghtStep;
    private  int mCountSteps;

    private int mWidth=70;
    private int mHeith=100;
    Drawable mImgPlayer;
    int mId;
/*
    Rect mImagePosition;
    Region mImageRegion;

    private int mTouchSlop;
    private int mTouchMode;

    static final int TOUCH_MODE_TAP = 1;
    static final int TOUCH_MODE_DOWN = 2;

    private int mScreenWidth, mScreenHeight;
*/

    boolean canImageMove;


public Player(Context context,int id){
    super(context);

    mId=id;
    mContext=context;
    init();
}

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("тыкнул на игрока").setMessage("Yeeees");
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void init(){
        setOnClickListener(this);
    }


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
    public void setLenghtSteps(int lenghtSteps){
        this.mLenghtStep=lenghtSteps;
    }
    public void setmCountSteps(int countSteps){
        this.mCountSteps= countSteps;
    }
    public int getmLenghtStep(){
        return this.mLenghtStep;
    }
    public int getmCountSteps(){
        return  this.mCountSteps;
    }
    /*public boolean onTouchEvent(MotionEvent event)
    {
        int positionX = (int)event.getRawX();
        int positionY = (int)event.getRawY();

        switch(event.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                mTouchMode = TOUCH_MODE_DOWN;

                if (mImageRegion.contains(positionX, positionY)) {
                    this.setPositionX( positionX);
                    this.setPositionY(positionY);

                    canImageMove = true;


                }
            }
            break;
            case MotionEvent.ACTION_MOVE:
            {
                if(canImageMove == true)
                {
                    // Check if we have moved far enough that it looks more like a
                    // scroll than a tap
                    final int distY = Math.abs(positionY - (int)getX());
                    final int distX = Math.abs(positionX - (int)getY());

                    if (distX > mTouchSlop || distY > mTouchSlop)
                    {
                        int deltaX =  positionX-this.getPositionX() ;
                        int deltaY =  positionY-this.getPositionY();

                        // Check if delta is added, is the rectangle is within the visible screen
                        if((mImagePosition.left+ deltaX) > 0 && ((mImagePosition.right +deltaX) < mScreenWidth )  && (mImagePosition.top +deltaY) >0 && ((mImagePosition.bottom+deltaY))<mScreenHeight)
                        {
                            // invalidate current position as we are moving...
                            mImagePosition.left = mImagePosition.left + deltaX;
                            mImagePosition.top = mImagePosition.top + deltaY;
                            mImagePosition.right = mImagePosition.left + mWidth;
                            mImagePosition.bottom = mImagePosition.top + mHeith;
                            mImageRegion.set(mImagePosition);
                            this.setPositionX(positionX);
                            this.setPositionY(positionY);

                            invalidate();
                        }
                    }
                }
            }
            break;
            case MotionEvent.ACTION_UP:
                canImageMove = false;
                break;
        }
        return true;
    }*/
    //рисуем игрока
    public void OnDraw(Canvas canvas){
        this.mImgPlayer = ContextCompat.getDrawable(mContext,mId);
        this.mImgPlayer .setBounds(this.getPositionX(), this.getPositionY(), this.getPositionX()+mWidth,this.getPositionY()+ mHeith);

        this.mImgPlayer .draw(canvas);

    }


}
