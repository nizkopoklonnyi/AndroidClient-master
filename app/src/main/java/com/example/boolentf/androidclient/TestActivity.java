package com.example.boolentf.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    String tokenAuthorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        tokenAuthorization = intent.getStringExtra("token");
        ((TextView)findViewById(R.id.test_text)).setText(tokenAuthorization);
    }
}
