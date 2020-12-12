package com.example.replace;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = GameActivity.class.getName();

    private ViewGroup mainLayout;
    private ImageView imgpbag;
    private ImageView imgpbottle;
    private ImageView imgmask;
    private ImageView imgcoca;
    private ImageView imgbintrash;
    private ImageView imgbinplastic;
    private ImageView imgbinpaper;

    private TextView pontoi;

    private int xDelta;
    private int yDelta;

    private int points;
    private Integer gamecounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mainLayout = (RelativeLayout) findViewById(R.id.main);
        imgpbag = (ImageView) findViewById(R.id.imgpbag);
        imgpbottle = (ImageView) findViewById(R.id.imgpbottle);
        imgmask = (ImageView) findViewById(R.id.imgmask);
        imgcoca = (ImageView) findViewById(R.id.imgcoca);

        imgbintrash = (ImageView) findViewById(R.id.imgbintrash);
        imgbinplastic = (ImageView) findViewById(R.id.imgbinplastic);
        imgbinpaper = (ImageView) findViewById(R.id.imgbinpaper);

        points =0;
        pontoi = (TextView) findViewById(R.id.pontoi);
        pontoi.setText("Ποντοι : "+points);

        imgpbag.setOnTouchListener(onTouchListener());
        imgpbottle.setOnTouchListener(onTouchListener());
        imgmask.setOnTouchListener(onTouchListener());
        imgcoca.setOnTouchListener(onTouchListener());
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:

                        Rect movingBound = new Rect();
                        view.getHitRect(movingBound);

                        Rect trashBound = new Rect();
                        imgbintrash.getHitRect(trashBound);

                        Rect paperBound = new Rect();
                        imgbinpaper.getHitRect(paperBound);

                        Rect plasticBound = new Rect();
                        imgbinplastic.getHitRect(plasticBound);

                        if(view.getContentDescription().equals("trash")){

                            if(trashBound.intersect(movingBound)){
                                Log.d(TAG,"Επιτυχης Σκουπιδι");
                                Toast.makeText(GameActivity.this,  "Επιτυχης Σκουπιδι", Toast.LENGTH_SHORT).show();
                                points+=5;
                                pontoi.setText("Ποντοι : "+Integer.toString(points));
                                gamecounter++;
                            }
                            else if(paperBound.intersect(movingBound)){
                                gamecounter++;
                            }
                            else if(plasticBound.intersect(movingBound)){
                                gamecounter++;
                            }
                        }
                        else if(view.getContentDescription().equals("paper")){

                            if(paperBound.intersect(movingBound)){
                                Log.d(TAG,"Επιτυχης Χαρτι");
                                Toast.makeText(GameActivity.this,  "Επιτυχης Χαρτι", Toast.LENGTH_SHORT).show();
                                points+=5;
                                pontoi.setText("Ποντοι : "+Integer.toString(points));
                                gamecounter++;
                            }
                            else if(trashBound.intersect(movingBound)){
                                gamecounter++;
                            }
                            else if(plasticBound.intersect(movingBound)){
                                gamecounter++;
                            }
                        }
                        else if(view.getContentDescription().equals("plastic")){


                            if(plasticBound.intersect(movingBound)){
                                Log.d(TAG,"Επιτυχης Πλαστικο");
                                Toast.makeText(GameActivity.this,  "Επιτυχης Πλαστικο", Toast.LENGTH_SHORT).show();
                                points+=5;
                                pontoi.setText("Ποντοι : "+Integer.toString(points) );
                                gamecounter++;
                            }
                            else if(paperBound.intersect(movingBound)){
                                gamecounter++;
                            }
                            else if(trashBound.intersect(movingBound)){
                                gamecounter++;
                            }

                        }

                        if(gamecounter == 4){
                            Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                            intent.putExtra("points",points);
                            startActivity(intent);
                        }

                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

}
