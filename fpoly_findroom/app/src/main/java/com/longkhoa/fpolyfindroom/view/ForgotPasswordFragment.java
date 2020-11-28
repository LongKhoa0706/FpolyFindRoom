package com.longkhoa.fpolyfindroom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.longkhoa.fpolyfindroom.R;

public class ForgotPasswordFragment extends AppCompatActivity {
    Toolbar tbDMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_mat_khau);
        tbDMK = findViewById(R.id.toolbarDoiMatKhau);
        setSupportActionBar(tbDMK);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}