package com.example.replace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    private ViewGroup mainLayout;
    private TextView go;
    private TextView gopoints;
    private Button button1;
    private Intent intent;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gameover);

        mainLayout = (RelativeLayout) findViewById(R.id.main2);
        go = (TextView) findViewById(R.id.gameo);
        gopoints = (TextView) findViewById(R.id.gopoints);
        button1 = (Button) findViewById(R.id.ok);

        intent = getIntent();
        points = intent.getIntExtra("points",0);

        go.setText("Game Over");
        gopoints.setText("Μπράβο user κέρδισες "+points+" πόντους");

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openNewActivity();
            }
        });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, PointGainActivity.class);
        startActivity(intent);
    }

}
