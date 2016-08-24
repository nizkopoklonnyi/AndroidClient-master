package com.example.boolentf.androidclient.Classes.GUI;

import android.content.Context;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.boolentf.androidclient.Classes.person.Player;
import com.example.boolentf.androidclient.R;

import org.springframework.core.io.Resource;

import java.util.ArrayList;

import static android.R.attr.left;

/**
 * Created by BoolenTF on 21.08.2016.
 */

//SurfaceView отображает компонент
 //SurfaceHolder.Callback опоаещение об изменениях
public class GUIpaint extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread mDrawThread;
    private Paint mPaint;
    private Resources res;
    private ArrayList<Player> mPlayers;
    Player examplePlayer;
    private Context mContex;

    private Bitmap mMap;
    Drawable room1;
    Drawable room2;
    Drawable room3;
    Drawable room4;
    Drawable room5;
    Drawable room6;
    /*Drawable d = ContextCompat.getDrawable(mContext, R.drawable.player1);
    d.setBounds(0, 0, 45, 45);
    d.draw(canvas);*/

    public GUIpaint(Context context) {
        super(context);
        mContex=context;
        res=this.getResources();
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
        private int displayHight;
        private int displayWidth;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;

            mMap =Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.map),2048,1536,true);


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
        room1.setBounds(0, 0, 600, 500);

        room2= ContextCompat.getDrawable(mContex, R.drawable.room2);
        room2.setBounds(0, 500, 600, 1000);

        room3= ContextCompat.getDrawable(mContex, R.drawable.room3);
        room3.setBounds(600, 0, 1200, 500);

        room4= ContextCompat.getDrawable(mContex, R.drawable.room4);
        room4.setBounds(600, 500,1200, 1000);

        room5= ContextCompat.getDrawable(mContex, R.drawable.room5);
        room5.setBounds(0, 1000, 600, 1500);

        room6= ContextCompat.getDrawable(mContex, R.drawable.room6);
        room6.setBounds(600, 1000, 1200, 1500);


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
}

