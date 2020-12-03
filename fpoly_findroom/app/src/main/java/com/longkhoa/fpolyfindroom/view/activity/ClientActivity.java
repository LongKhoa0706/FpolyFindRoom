package com.longkhoa.fpolyfindroom.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.view.OptionAuthFragment;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameClient, new OptionAuthFragment()).commit();
    }
}