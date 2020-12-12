package com.example.replace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PointGainActivity extends AppCompatActivity {

    private Button gamebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.points_gain_main);

        gamebutton = (Button) findViewById(R.id.button);
        gamebutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(PointGainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
