package com.longkhoa.fpolyfindroom.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.longkhoa.fpolyfindroom.R;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, OnBoardingActivity.class));
//                overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);
//                startService(new Intent(SplashScreenActivity.this, MessagingService.class));

                finish();
            }

        }, 3000);
    }
}