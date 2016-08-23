package com.example.boolentf.androidclient.Classes.GUI;

import android.content.Context;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.boolentf.androidclient.R;

import org.springframework.core.io.Resource;

/**
 * Created by BoolenTF on 21.08.2016.
 */
public class GUIpaint extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    private Paint mPaint;
    private Resources res;

    private Bitmap lvlCounter;

    public GUIpaint(Context context) {
        super(context);
        res=this.getResources();
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
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
            lvlCounter = BitmapFactory.decodeResource(res, R.drawable.lvl_counter_green);
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
                        // mPaint.setColor(Color.WHITE);
                        canvas.drawColor(Color.WHITE);
                        // lvlCounter = BitmapFactory.decodeResource(res, R.drawable.lvl_counter_green);
                        canvas.drawBitmap(lvlCounter,getPercentInt(displayWidth,2),displayHight-(lvlCounter.getHeight()/2),null);
                        }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
public int getPercentInt(int integer, int percent){
    return (integer*percent)/100;
}
}

