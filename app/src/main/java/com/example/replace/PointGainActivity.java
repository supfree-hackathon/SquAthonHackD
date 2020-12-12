package com.example.replace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PointGainActivity extends AppCompatActivity {

    private Button PlayQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.points_gain_main);

        PlayQuizButton = (Button) findViewById(R.id.button6);
        PlayQuizButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ToDo
                //getBaseContext() isws na mh doulepsei
                Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
