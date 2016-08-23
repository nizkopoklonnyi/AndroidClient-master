package com.example.boolentf.androidclient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.boolentf.androidclient.Classes.DATA.GameForTransaction;
import com.example.boolentf.androidclient.Classes.REST.RestRequest;
import com.example.boolentf.androidclient.Classes.adapters.GamesListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GamesListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String tokenAuthorization;
    private HttpGameListRequestTask gameListTask;
    private Context context;
    private boolean taskIsActive=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        context=this;

        Intent intent = getIntent();
        tokenAuthorization = intent.getStringExtra("token");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        gameListTask=new HttpGameListRequestTask();
        this.gameListTask.execute(tokenAuthorization);

    }

    public void onClickGameList(View view){
        Intent intent;
        switch(view.getId()){
          case R.id.create_game_button_list:
              intent=new Intent(context, CreateGameActivity.class);
              intent.putExtra("token",tokenAuthorization);
              startActivity(intent);
              break;
          case R.id.join_game_button_list:
              intent=new Intent(context, JoinGameActivity.class);
              intent.putExtra("token",tokenAuthorization);
              startActivity(intent);
              break;
        }
    }

    //запрос на получение списка
    class HttpGameListRequestTask extends AsyncTask<String, Void, Void> {

        Boolean isErrored = false;
        String status;
        GameForTransaction mGamesData[];
        List<GameForTransaction> mGamesDataError;

        @Override
        protected void onPreExecute() {
            taskIsActive=true;
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                RestRequest rest = new RestRequest();
                mGamesData = rest.in("game/all-games").GetObjAndStatus(GameForTransaction[].class, params[0]);
                status = rest.getStatus();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                status = "error!4XX loadingGames";
                isErrored = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (isErrored) {
                ///////////сообщение об ошибке///////////////
                mGamesDataError= new ArrayList<GameForTransaction>();
                mGamesData=new GameForTransaction[1];
                mGamesData[0].setName(status);
                mGamesData[0].setPassword("sss");
                mGamesData[0].setOpen(true);
                mGamesDataError.add(mGamesData[0]);
                /////////////////////////////////////////
            }
            else
            {
                mGamesDataError=Arrays.asList(mGamesData);
            }
            mAdapter = new GamesListAdapter(mGamesDataError);
            mRecyclerView.setAdapter(mAdapter);
            taskIsActive=false;
        }
    }
}