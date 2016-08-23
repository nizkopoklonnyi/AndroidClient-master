package com.example.boolentf.androidclient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.boolentf.androidclient.Classes.DATA.GameForTransaction;
import com.example.boolentf.androidclient.Classes.DATA.UserForTransaction;
import com.example.boolentf.androidclient.Classes.REST.RestRequest;

import java.util.List;

public class JoinGameActivity extends AppCompatActivity {

    private String tokenAuthorization;
    private TextView statusText;
    private Context context;
    GameForTransaction gameData;
    EditText nameIn;
    EditText passwordIn;
    CheckBox isOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        context=this;
        gameData=new GameForTransaction();
        nameIn=(EditText) findViewById(R.id.name_join_game);
        passwordIn=(EditText) findViewById(R.id.password_join_game);

        Intent intent = getIntent();
        tokenAuthorization = intent.getStringExtra("token");

        statusText=(TextView) findViewById(R.id.status_text_join_game);
        isOpen=(CheckBox)findViewById(R.id.is_open_join_game);

        //if(isOpen.isChecked())
          //  passwordIn.setVisibility(View.VISIBLE);
        //else
         //   passwordIn.setVisibility(View.INVISIBLE);

    }

    public void onClickJoinGame(View view) {
        gameData.setName( nameIn.getText().toString());
        gameData.setPassword( passwordIn.getText().toString());
        gameData.setOpen(isOpen.isChecked());
        switch(view.getId()){
            case R.id.join_button_join_game:
                new HttpGameJoinRequestTask().execute(gameData);
                break;
            case R.id.is_open_join_game:
                if(isOpen.isChecked())
                    passwordIn.setVisibility(View.VISIBLE);
                else
                    passwordIn.setVisibility(View.INVISIBLE);
                break;
        }
    }


    class HttpGameJoinRequestTask extends AsyncTask<GameForTransaction, Void, Void> {

        Boolean isErrored = false;
        String status;
        UserForTransaction mGamesData[];
        String token;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(R.id.join_button_join_game).setVisibility(View.INVISIBLE);
            token=tokenAuthorization;
        }

        @Override
        protected Void doInBackground(GameForTransaction... params) {

            try {
                RestRequest rest = new RestRequest();
                mGamesData =  rest.in("game/join").PostObjGetObjAndStatus(params[0],UserForTransaction[].class,token);
                status = rest.getStatus();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                status = "error!4XX JoinGame";
                isErrored = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            findViewById(R.id.join_button_join_game).setVisibility(View.VISIBLE);
            super.onPostExecute(result);
            statusText.setText(status);
            if (!isErrored) {
                Intent intent = new Intent(context,GamePlayActivity.class);
                intent.putExtra("token",tokenAuthorization);
            }
        }
    }
}
