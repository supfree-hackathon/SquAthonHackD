package com.example.replace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class IntroActivity extends AppCompatActivity {

    Button skipButton;
    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    TabLayout tabLayout;
    int[] images = {R.drawable.first_image, R.drawable.second_image, R.drawable.third_image, R.drawable.fourth_image, R.drawable.fifth_image, R.drawable.sixth_image};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);
        mViewPagerAdapter = new ViewPagerAdapter(IntroActivity.this, images);
        mViewPager.setAdapter(mViewPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        skipButton = (Button) findViewById(R.id.buttonSkip);
        skipButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openNewActivity();
            }
        });

    }

    public void openNewActivity(){
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
    }
}