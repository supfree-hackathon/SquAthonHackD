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
    private TextView textView3;
    private TextView textView4;
    private Button button1;
    private Intent intent;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gameover);

        mainLayout = (RelativeLayout) findViewById(R.id.main2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        button1 = (Button) findViewById(R.id.ok);

        intent = getIntent();
        points = intent.getIntExtra("points",0);

        textView3.setText("Μπράβο user κέρδισες");
        textView3.setText(points+" πόντους");

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openNewActivity();
            }
        });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }

}
