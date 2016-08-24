package com.example.boolentf.androidclient.Classes.map;

/**
 * Created by newUser on 24.08.2016.
 */

public class Map {
    private int mMinX,mMinY,mMaxX,mMaxY;

    public Map(int minX,int minY,int maxX,int maxY){
        mMinX=minX;
        mMinY=minY;
        mMaxX=maxX;
        mMaxY=maxY;
    }
    public void setmMinX(int x){
        this.mMinY=x;
    }

    public void setmMinY(int y) {
       this.mMinY=y;
    }
    public void setmMaxX(int x){
        this.mMaxX=x;
    }

    public void setmMaxY(int mMaxY) {
        this.mMaxY = mMaxY;
    }

    public int getmMinX() {
        return mMinX;
    }

    public int getmMinY() {
        return mMinY;
    }

    public int getmMaxX() {
        return mMaxX;
    }

    public int getmMaxY() {
        return mMaxY;
    }
}
