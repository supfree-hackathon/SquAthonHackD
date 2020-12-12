package com.example.replace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        skipButton = (Button) findViewById(R.id.buttonSkip);
        skipButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openNewActivity();
            }
        });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}