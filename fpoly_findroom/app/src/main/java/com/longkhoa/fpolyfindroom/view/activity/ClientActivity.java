package com.longkhoa.fpolyfindroom.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.view.OptionAuthFragment;

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