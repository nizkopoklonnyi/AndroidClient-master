package com.example.boolentf.androidclient.Classes.GUI;

import android.app.Activity;
import android.content.Context;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.example.boolentf.androidclient.Classes.map.Map;
import com.example.boolentf.androidclient.Classes.person.Player;
import com.example.boolentf.androidclient.R;
import java.util.ArrayList;


/**
 * Created by BoolenTF on 21.08.2016.
 */

//SurfaceView отображает компонент
 //SurfaceHolder.Callback опоаещение об изменениях
public class GUIpaint extends SurfaceView implements SurfaceHolder.Callback {

    private static final int INVALID_POINTER_ID = -1;

    private static final int MAP_WIDTH = 2048;
    private static final int MAP_HEIGHT= 1536;
    private int mScreenWidth;
    private int mScreenHeight;


    private DrawThread mDrawThread;
    private Paint mPaint;
    private Resources res;
    private ArrayList<Player> mPlayers;
    Player mOrangePlayerM;
    Player mGreenPlayerW;
    Player mVioletPlayerM;

    private Context mContex;

    private  Map mWorkArea;

    private Bitmap mMap;
/** global*/
    private int x=0,y=0;
    private int mWidth=600, mHeith=500;

    private float mLastTouchX;
    private float mLastTouchY;

    private Drawable room1;
    private Drawable room2;
    private Drawable room3;
    private Drawable room4;
    private Drawable room5;
    private Drawable room6;
    private Drawable room7;
    private Drawable room8;
    private Drawable room9;
    private Drawable room10;
    private Drawable room11;
    private Drawable room12;

    private Drawable mLightSwordCard;
    private Drawable mWorriorCard;


    public GUIpaint(Context context) {
        super(context);
        mContex=context;
        getScreenSize();
        res=this.getResources();
        mWorkArea= new Map(mContex);
        getHolder().addCallback(this);
    }

    //изменен формат или размер SurfaceView
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //SurfaceView создан и готов к отображению информации
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mDrawThread = new DrawThread(getHolder());
        mDrawThread.setRunning(true);
        mDrawThread.start();
    }

    //вызывается перед тем, как SurfaceView будет уничтожен
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mDrawThread.setRunning(false);
        while (retry) {
            try {
                mDrawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }


    class DrawThread extends Thread {

        private boolean running = false;
        private SurfaceHolder surfaceHolder;


        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;

            mMap =Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.map),MAP_WIDTH,MAP_HEIGHT,true);
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {

                    canvas = surfaceHolder.lockCanvas(null);

                    synchronized (surfaceHolder){


                        //выбор карты
                        canvas.drawBitmap(mMap,0,0,null);

                        setRooms(canvas);

                        //рисуем игроков
                        drawPlatyers(canvas);

                        //выбор уровня
                       // canvas.drawBitmap(mLvlBitmap,getPercentInt(displayWidth,2),displayHight-(mLvlBitmap.getHeight()/2),null);


                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
    public void getComponents(ArrayList<Player> players ){
        this.mPlayers=players;
    }

    private void setRooms(Canvas canvas){

        room1= ContextCompat.getDrawable(mContex, R.drawable.start_room);
        room1.setBounds(x, y, x+mWidth, mHeith);

        room2= ContextCompat.getDrawable(mContex, R.drawable.room2);
        room2.setBounds(x, y+mHeith, x+mWidth, mHeith*2);

        room3= ContextCompat.getDrawable(mContex, R.drawable.room3);
        room3.setBounds(x, y+mHeith*2, mWidth, mHeith*3);

        room4= ContextCompat.getDrawable(mContex, R.drawable.room4);
        room4.setBounds(x+mWidth, y+mHeith,mWidth*2, mHeith*2);


        room5= ContextCompat.getDrawable(mContex, R.drawable.room5);
        room5.setBounds(x+mWidth, y, mWidth*2, mHeith);

        room6= ContextCompat.getDrawable(mContex, R.drawable.room6);
        room6.setBounds(x+mWidth, y+mHeith*2, mWidth*2, mHeith*3);

        room7= ContextCompat.getDrawable(mContex, R.drawable.room7);
        room7.setBounds(x+mWidth*2, y, mWidth*3, mHeith);

        room8= ContextCompat.getDrawable(mContex, R.drawable.room8);
        room8.setBounds(x+mWidth*2, y+mHeith, mWidth*3, mHeith*2);


        room9= ContextCompat.getDrawable(mContex, R.drawable.room9);
        room9.setBounds(x+mWidth*2, y+mHeith*2, mWidth*3, mHeith*3);

        room10= ContextCompat.getDrawable(mContex, R.drawable.room10);
        room10.setBounds(x, y+mHeith*3, mWidth, mHeith*4);

        room11= ContextCompat.getDrawable(mContex, R.drawable.room11);
        room11.setBounds(x+mWidth, y+mHeith*3, mWidth*2, mHeith*4);

        room12= ContextCompat.getDrawable(mContex, R.drawable.room12);
        room12.setBounds(x+mWidth*2, y+mHeith*3, mWidth*3, mHeith*4);


        room1.draw(canvas);
        room2.draw(canvas);
        room3.draw(canvas);
        room4.draw(canvas);
        room5.draw(canvas);
        room6.draw(canvas);
        room7.draw(canvas);
        room8.draw(canvas);
        room9.draw(canvas);
        room10.draw(canvas);
        room11.draw(canvas);
        room12.draw(canvas);

    }

    private void drawPlatyers(Canvas canvas){
        mOrangePlayerM = new Player(mContex, R.drawable.orange_m);
        mOrangePlayerM.setPositionX(240);
        mOrangePlayerM.setPositionY(190);
        mOrangePlayerM.OnDraw(canvas);

        mGreenPlayerW= new Player(mContex,R.drawable.green_w);
        mGreenPlayerW.setPositionX(325);
        mGreenPlayerW.setPositionY(190);
        mGreenPlayerW.OnDraw(canvas);

        mVioletPlayerM= new Player(mContex,R.drawable.violet_m);
        mVioletPlayerM.setPositionX(280);
        mVioletPlayerM.setPositionY(300);
        mVioletPlayerM.OnDraw(canvas);
    }


public int getPercentInt(int integer, int percent){
    return (integer*percent)/100;
}


public void getScreenSize(){
    DisplayMetrics metrics = new DisplayMetrics();

    ((Activity) getContext()).getWindowManager()
            .getDefaultDisplay()
            .getMetrics(metrics);

     mScreenWidth =  metrics.widthPixels;
     mScreenHeight= metrics.heightPixels;
}

}
