package com.example.iramli.tap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void easy(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("minutes", 4000);
        startActivity(intent);
    }

    public void middle(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("minutes", 3000);
        startActivity(intent);
    }

    public void hard(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("minutes", 2500);
        startActivity(intent);

    }

    public void extra(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("minutes", 2000);
        startActivity(intent);

    }
}
