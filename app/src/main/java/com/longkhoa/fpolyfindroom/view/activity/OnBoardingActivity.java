package com.longkhoa.fpolyfindroom.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.SlideAdapter;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;

import retrofit2.converter.gson.GsonConverterFactory;

public class OnBoardingActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout linearLayout;
    SlideAdapter slideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPager = findViewById(R.id.viewPagerOnboard);

//        linearLayout = findViewById(R.id.dotViewPager);
        slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2){
                    startActivity(new Intent(OnBoardingActivity.this, ClientActivity.class));
                    finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}