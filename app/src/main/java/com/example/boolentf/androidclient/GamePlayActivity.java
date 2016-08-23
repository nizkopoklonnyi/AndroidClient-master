package com.example.boolentf.androidclient;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.boolentf.androidclient.Classes.GUI.GUIpaint;
import com.example.boolentf.androidclient.Classes.person.Player;

public class GamePlayActivity extends AppCompatActivity implements View.OnTouchListener {

    private Paint mPaint = new Paint();
    Player userPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GUIpaint guiPaint=new GUIpaint(this);
        setContentView(guiPaint);
        guiPaint.setOnTouchListener(this);

        userPlayer=new Player();
        userPlayer.setColor("green", this);



    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        boolean inTouch=false;
        // событие
        int actionMask = event.getActionMasked();
        // индекс касания
        int pointerIndex = event.getActionIndex();
        // число касаний
        int pointerCount = event.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: // первое касание
                inTouch = true;

            case MotionEvent.ACTION_MOVE: // движение

                if (pointerCount>2) {
                    event.getX(0);
                    event.getY(0);
                    break;
                }
            }

        return true;
    }
    public Player getUserPlayer(){
        return userPlayer;
    }

}
