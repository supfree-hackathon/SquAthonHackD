package com.example.replace;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

import static androidx.core.content.ContextCompat.startActivity;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_one, btn_two, btn_three, btn_four;
    TextView tv_question;
    private QuizQuestionnaire question = new QuizQuestionnaire();
    private String answer;
    private int questionLength = question.questions.length;
    int random;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        random = 0;
        btn_one = (Button)findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = (Button)findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = (Button)findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);
        btn_four = (Button)findViewById(R.id.btn_four);
        btn_four.setOnClickListener(this);
        tv_question = (TextView)findViewById(R.id.tv_question);
        NextQuestion(random++);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                if(btn_one.getText() == answer){
                    //print "YOU ARE CORRECT"
                    NextQuestion(random++);
                }else{
                    //You are WRONG
                    NextQuestion(random++);
                }
                break;
            case R.id.btn_two:
                if(btn_two.getText() == answer){
                    //print "YOU ARE CORRECT"
                    NextQuestion(random++);
                }else{
                    //You are WRONG
                    NextQuestion(random++);
                }
                break;
            case R.id.btn_three:
                if(btn_three.getText() == answer){
                    //print "YOU ARE CORRECT"
                    NextQuestion(random++);
                }else{
                    //You are WRONG
                    NextQuestion(random++);
                }
                break;
            case R.id.btn_four:
                if(btn_four.getText() == answer){
                    //print "YOU ARE CORRECT"
                    NextQuestion(random++);
                }else{
                    //You are WRONG
                    NextQuestion(random++);
                }
                break;
        }
    }

    private void GameOver(){
        /*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizQuestionnaire.this);
        alertDialogBuilder
                .setMessage("Game Over")
                .setCancelable(false)
                .setPositiveButton("Πίσω στην Αρχική", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(this, PointGainActivity.class);
                    }
                });
        alertDialogBuilder.show();
        */
        Intent intent = new Intent(QuizActivity.this, PointGainActivity.class);
        startActivity(intent);
    }
    private void NextQuestion(int num){
        tv_question.setText(question.getQuestion(num));
        btn_one.setText(question.getchoice1(num));
        btn_two.setText(question.getchoice2(num));
        btn_three.setText(question.getchoice3(num));
        btn_four.setText(question.getchoice4(num));
        answer = question.getCorrectAnswer(num);
    }
    }

