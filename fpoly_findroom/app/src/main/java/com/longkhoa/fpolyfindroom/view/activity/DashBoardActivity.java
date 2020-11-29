package com.longkhoa.fpolyfindroom.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.view.AddInfoRoomFragment;
import com.longkhoa.fpolyfindroom.view.favorite.FavoriteFragment;
import com.longkhoa.fpolyfindroom.view.home.HomeFragment;
import com.longkhoa.fpolyfindroom.view.MapFragment;
import com.longkhoa.fpolyfindroom.view.ProfileFragment;

public class DashBoardActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    private static final String TAG = MainActivity.class.getSimpleName() ;
    FragmentManager manager;
    Fragment fragment= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        chipNavigationBar = findViewById(R.id.bottomTrangChu);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.dashboard_frame, new HomeFragment())
                    .commit();
            chipNavigationBar.setItemSelected(R.id.home, true);
            chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

                @Override
                public void onItemSelected(int id) {
                    switch (id) {
                        case R.id.home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.menuu:
                            fragment = new FavoriteFragment();
                            break;
                        case R.id.cuahang:
                            fragment = new MapFragment();
                            break;
                        case R.id.taikhoan:
                            fragment = new ProfileFragment();
                            break;
                    }
                    if (fragment != null) {
                        manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.dashboard_frame, fragment).commit();
                    } else {
                        Log.e(TAG, "Erro");
                    }

                }
            });
        }


    }
}