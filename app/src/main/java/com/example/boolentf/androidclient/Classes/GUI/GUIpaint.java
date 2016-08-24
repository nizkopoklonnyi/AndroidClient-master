package com.example.boolentf.androidclient.Classes.GUI;

import android.app.Activity;
import android.content.Context;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.boolentf.androidclient.Classes.map.Map;
import com.example.boolentf.androidclient.Classes.person.Player;
import com.example.boolentf.androidclient.R;

import org.springframework.core.io.Resource;

import java.util.ArrayList;

import static android.R.attr.left;
import static android.R.attr.screenSize;

/**
 * Created by BoolenTF on 21.08.2016.
 */

//SurfaceView отображает компонент
 //SurfaceHolder.Callback опоаещение об изменениях
public class GUIpaint extends SurfaceView implements SurfaceHolder.Callback {

    private static final int MAP_WIDTH = 2048;
    private static final int MAP_HEIGHT= 1536;
    private int mScreenWidth;
    private int mScreenHeight;


    private DrawThread mDrawThread;
    private Paint mPaint;
    private Resources res;
    private ArrayList<Player> mPlayers;
    Player examplePlayer;
    private Context mContex;

    private Bitmap mMap;

    private int x=0,y=0, mWidth=600, mHeith=500;

    private Drawable room1;
    private Drawable room2;
    private Drawable room3;
    private Drawable room4;
    private Drawable room5;
    private Drawable room6;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private Map mVisibleMap;

    private int mCurrentX=0, mCurrentY=0;

    // = new Map(0, 0, WIDTH_SCREEN, HEITH_SCREEN);

    private  ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener;

    public GUIpaint(Context context) {
        super(context);
        mContex=context;
        getScreenSize();
        res=this.getResources();
        getHolder().addCallback(this);

      //  mScaleDetector = new ScaleGestureDetector(context,);
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
        private int displayHight;
        private int displayWidth;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;

            mMap =Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.map),MAP_WIDTH,MAP_HEIGHT,true);


            //BitmapFactory.decodeResource(res, R.drawable.start_room);
            //BitmapFactory.decodeResource(res, R.drawable.start_room);
            //BitmapFactory.decodeResource(res, R.drawable.lvl_counter_green);

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
                        displayHight=canvas.getHeight();
                        displayWidth=canvas.getWidth();

                        canvas.drawColor(Color.WHITE);

                        //выбор уровня
                       // canvas.drawBitmap(mLvlBitmap,getPercentInt(displayWidth,2),displayHight-(mLvlBitmap.getHeight()/2),null);


                        //выбор карты
                        canvas.drawBitmap(mMap,0,0,null);

                        setRooms(canvas);

                        //проба игрока
                        examplePlayer= new Player(mContex);
                        examplePlayer.OnDraw(canvas);

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


        room1.draw(canvas);
        room2.draw(canvas);
        room3.draw(canvas);
        room4.draw(canvas);
        room5.draw(canvas);
        room6.draw(canvas);
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
    public void viewScreen(){

    }
}
