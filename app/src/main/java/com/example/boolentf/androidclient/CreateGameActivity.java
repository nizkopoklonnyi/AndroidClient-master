package com.example.boolentf.androidclient;

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
import com.example.boolentf.androidclient.Classes.REST.RestRequest;

import java.util.List;

public class CreateGameActivity extends AppCompatActivity {

    CheckBox isOpen;
    EditText gameName;
    EditText gamePassword;
    String tokenAuthorization;
    TextView statusText;
    GameForTransaction gameForTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        Intent intent = getIntent();
        tokenAuthorization = intent.getStringExtra("token");

        gameForTransaction =new GameForTransaction();

        statusText=(TextView) findViewById(R.id.status_text_create_game);

        gameName=(EditText) findViewById(R.id.game_name_edit_create);
        gamePassword=(EditText) findViewById(R.id.game_password_edit_create);
        isOpen=(CheckBox) findViewById(R.id.is_open_game_create);
        if(isOpen.isChecked())
            gamePassword.setVisibility(View.VISIBLE);
        else
            gamePassword.setVisibility(View.INVISIBLE);
    }
    public void onClickGeameCreate(View view){
        switch(view.getId()){
            case R.id.create_game_button_create_game:
                gameForTransaction.setName(gameName.getText().toString());
                gameForTransaction.setOpen(isOpen.isChecked());
                if(isOpen.isChecked())
                    gameForTransaction.setPassword(gamePassword.getText().toString());
                else
                    gameForTransaction.setPassword("lol");
                new HttpGameCreateRequestTask().execute(gameForTransaction);
                break;
            case R.id.is_open_game_create:
                if(isOpen.isChecked())
                    gamePassword.setVisibility(View.VISIBLE);
                else
                    gamePassword.setVisibility(View.INVISIBLE);
                break;
        }
    }
//Создает игру
    class HttpGameCreateRequestTask extends AsyncTask<GameForTransaction, Void, Void> {

        Boolean isErrored = false;
        String status;
        List<GameForTransaction> mGamesData;
        String token;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(R.id.create_game_button_create_game).setVisibility(View.INVISIBLE);
            token=tokenAuthorization;
        }

        @Override
        protected Void doInBackground(GameForTransaction... params) {

            try {
                RestRequest rest = new RestRequest();
                rest.in("game/create").PostObjGetObjAndStatus(params[0],null,token);
                status = rest.getStatus();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                status = "error!4XX CreateGame token:"+token;
                isErrored = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            findViewById(R.id.create_game_button_create_game).setVisibility(View.VISIBLE);
            super.onPostExecute(result);
            statusText.setText(status);
            if (!isErrored) {
                Intent intent = new Intent();
                intent.putExtra("token",tokenAuthorization);
            }
        }
    }

}
