package com.longkhoa.fpolyfindroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameClient, new OptionAuthFragment()).commit();
    }
}