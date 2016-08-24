package view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.example.boolentf.androidclient.Classes.person.Player;

/**
 * Created by newUser on 24.08.2016.
 */

public class DrawPerson extends View {

    private Player person;

    public DrawPerson(Context context){
        super(context);
    }


    public void onDraw(Canvas canvas)
    {
        person.draw(canvas);
    }
}
