package com.longkhoa.fpolyfindroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class OnBoardingScreenActivity extends AppCompatActivity {
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

    }
}