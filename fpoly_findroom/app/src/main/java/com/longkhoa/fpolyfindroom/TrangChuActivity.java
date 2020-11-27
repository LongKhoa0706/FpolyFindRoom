package com.longkhoa.fpolyfindroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class TrangChuActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    private static final String TAG = MainActivity.class.getSimpleName() ;
    FragmentManager manager;
    Fragment fragment= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        chipNavigationBar = findViewById(R.id.bottomTrangChu);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame1, new TrangChuFragment())
                    .commit();
            chipNavigationBar.setItemSelected(R.id.home, true);
            chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

                @Override
                public void onItemSelected(int id) {
                    switch (id) {
                        case R.id.home:
                            fragment = new TrangChuFragment();
                            break;
                        case R.id.menuu:
                            fragment = new YeuThichFragment();
                            break;
                        case R.id.cuahang:
                            fragment = new MapFragment();
                            break;
                        case R.id.taikhoan:
                            fragment = new TaiKhoanFragment();
                            break;
                    }
                    if (fragment != null) {
                        manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.frame1, fragment).commit();
                    } else {
                        Log.e(TAG, "Erro");
                    }

                }
            });
        }


    }
}