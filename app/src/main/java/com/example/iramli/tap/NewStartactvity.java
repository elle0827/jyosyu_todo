package com.example.iramli.tap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewStartactvity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newstartactvity);
    }
    public void start(View v){
        Intent intent = new Intent(NewStartactvity.this,StartActivity.class);
        startActivity(intent);

    }
}
