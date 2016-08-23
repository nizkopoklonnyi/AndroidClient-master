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

import com.example.boolentf.androidclient.Classes.DATA.UserForTransaction;
import com.example.boolentf.androidclient.Classes.REST.RestRequest;

public class RegistrationActivity extends AppCompatActivity {

    Context context = this;
    TextView text;
    UserForTransaction userForTransaction;
    EditText loginIn;
    EditText passwordIn;
    EditText password_check;
    TextView notice;
    static final String BAD_PASSWORD="Неверно введен пароль";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);
        loginIn=(EditText) findViewById(R.id.login_reg);
        passwordIn=(EditText) findViewById(R.id.password_reg);
        password_check=(EditText) findViewById(R.id.password_check);
        text=(TextView) findViewById(R.id.status_reg);
        notice=(TextView) findViewById(R.id.notice);
        userForTransaction = new UserForTransaction();
    }

    public void onClickRegisration(View view){
        if(password_check.getText().toString().equals(passwordIn.getText().toString() ) ){
            userForTransaction.setLogin(loginIn.getText().toString());
            userForTransaction.setPassword(passwordIn.getText().toString());
            new HttpRegistrationRequestTask().execute(userForTransaction);
        }
        else{
                notice.setText(BAD_PASSWORD);
        }
    }

    //запрос на регистарцию.Запихнуть в метод Кнопки.
    class HttpRegistrationRequestTask extends AsyncTask<UserForTransaction, Void, Void> {

        String status;
        String tokenAuthorization;
        Boolean isErrored=false;

        @Override
        protected void onPreExecute() {
            findViewById(R.id.registration_button_registration/*!!!id кнопки регистратции!!!*/).setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(UserForTransaction... params) {

            try {

                RestRequest rest = new RestRequest();
                rest.in("user/registration").PostObjGetObjAndStatus(params[0],Void.class);
                status = rest.getStatus();
                /*Возможно стоит встваить:
                if(rest.getStatus()!="200")
                    response="error!4XX Registration..."
                    isErrored=false;;*/
                //
                try {
                    tokenAuthorization=(String)rest.in("user/login").PostObjGetObjAndStatus(params[0],String.class);
                    /*Возможно стоит вставить
                    if(rest.getStatus()!="200")
                        response="error!4XX Login..."
                        isErrored=false;;
                    */
                }
                catch (Exception e) {
                    Log.e("MainActivity"/*!!!название Activity*/, e.getMessage(), e);
                    status="error!4XX Login...";
                    isErrored=true;
                }
            } catch (Exception e) {
                Log.e("MainActivity"/*!!!название Activity*/, e.getMessage(), e);
                status="error!4XXRegistration...";
                isErrored=true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //!!!метод ниже, передает стату в view строку, предуприждая пользователя об ошибке регистрации.
            text.setText(status);
            findViewById(R.id.registration_button_registration/*!!id кнопки регистратции*/).setVisibility(View.VISIBLE);
            if(!isErrored){
                Intent intent = new Intent(context, GamesListActivity.class);
                intent.putExtra("token",tokenAuthorization);
                startActivity(intent);}
        }
    }
    }

