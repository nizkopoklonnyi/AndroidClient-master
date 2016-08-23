package com.example.boolentf.androidclient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.boolentf.androidclient.Classes.DATA.GameForTransaction;
import com.example.boolentf.androidclient.Classes.DATA.UserForTransaction;
import com.example.boolentf.androidclient.Classes.REST.RestRequest;

public class MainActivity extends AppCompatActivity {

    String response;
    TextView text;
    UserForTransaction dataTest;
    GameForTransaction dataTestGame;
    EditText loginIn;
    EditText passwordIn;
    Context context = this;
    HttpLoginRequestTask taskLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataTest=new UserForTransaction();
        text=(TextView) findViewById(R.id.text);
        loginIn = (EditText)findViewById(R.id.login_in);
        passwordIn=(EditText)findViewById(R.id.password_in);
        //game activity тест//

    }

    //ОБРАБОТЧИКИ
    //Логин кнопка
    public void onClickLogin(View view){
        dataTest.setLogin(loginIn.getText().toString());
        dataTest.setPassword(passwordIn.getText().toString());

        taskLogin= new HttpLoginRequestTask();//.execute(dataTest);
        taskLogin.execute(dataTest);
    }
    //Регистрация кнопка
    public void onClickReginstation( View view){

        dataTest.setLogin(loginIn.getText().toString());
        dataTest.setPassword(passwordIn.getText().toString());

        Intent intent= new Intent(this, RegistrationActivity.class);
        startActivity(intent);

    }
    //Тест кнопка
public void onClickTest(View view){
    dataTest.setLogin(loginIn.getText().toString());
    dataTest.setPassword(passwordIn.getText().toString());

    Intent intent= new Intent(this, GamePlayActivity.class);
    startActivity(intent);
}

//запрос на Авторизацию
    class HttpLoginRequestTask extends AsyncTask<UserForTransaction, Void, Void> {

    Boolean isErrored=false;
    String status;
    String tokenAuthorization;

    @Override
    protected void onPreExecute() {
        findViewById(R.id.registration_button_login).setVisibility(View.INVISIBLE);
        findViewById(R.id.login_button_login).setVisibility(View.INVISIBLE);
    }

    @Override
        protected Void doInBackground(UserForTransaction... params) {

            try {
                RestRequest rest = new RestRequest();
                tokenAuthorization=(String) rest.in("user/login").PostObjGetObjAndStatus(params[0],String.class);
                status=rest.getStatus();
            }
            catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                status="error!4XX Login";
                isErrored=true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            text.setText(status);
            findViewById(R.id.registration_button_login).setVisibility(View.VISIBLE);
            findViewById(R.id.login_button_login).setVisibility(View.VISIBLE);
            if(!isErrored){
                Intent intent = new Intent(context, GamesListActivity.class);
                intent.putExtra("token",tokenAuthorization);
                startActivity(intent);}
        }

    }
//Game запрос
    /*
    class HttpGameRequestTask extends AsyncTask<GameForTransaction, Void, Void> {

        @Override
        protected void onPreExecute() {
            findViewById(R.id.registration).setVisibility(View.INVISIBLE);
            findViewById(R.id.login).setVisibility(View.INVISIBLE);
            findViewById(R.id.new_room).setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(GameForTransaction... params) {

            try {
                RestRequest rest = new RestRequest();
                rest.in("game/create").PostObjGetObjAndStatus(params[0],Void.class,"lololol");
                response= "statusCode:" + rest.getStatus();
            }
            catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                response="error!4XX Game";
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            text.setText(response);
            findViewById(R.id.registration).setVisibility(View.VISIBLE);
            findViewById(R.id.login).setVisibility(View.VISIBLE);
            findViewById(R.id.new_room).setVisibility(View.VISIBLE);
        }

    }*/
}