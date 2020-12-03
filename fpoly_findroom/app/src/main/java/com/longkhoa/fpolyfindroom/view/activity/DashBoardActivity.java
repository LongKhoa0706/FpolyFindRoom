package com.longkhoa.fpolyfindroom.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.view.AddInfoRoomFragment;
import com.longkhoa.fpolyfindroom.view.DetailRoomFragment;
import com.longkhoa.fpolyfindroom.view.GetAllRoomFragment;
import com.longkhoa.fpolyfindroom.view.MapFragment;
import com.longkhoa.fpolyfindroom.view.ProfileFragment;
import com.longkhoa.fpolyfindroom.view.favorite.FavoriteFragment;
import com.longkhoa.fpolyfindroom.view.home.HomeFragment;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;


public class DashBoardActivity extends AppCompatActivity {
    SpaceNavigationView spaceNavigationView;
    private static final String TAG = MainActivity.class.getSimpleName() ;
    FragmentManager manager;
    Fragment fragment= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        spaceNavigationView = findViewById(R.id.bottomBar);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
//        spaceNavigationView.changeCenterButtonIcon(R.drawable.baseline_add_white_18dp);
        spaceNavigationView.setSpaceBackgroundColor(ContextCompat.getColor(this, R.color.white));
        spaceNavigationView.setCentreButtonIcon(R.drawable.baseline_add_white_18dp);
        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this, R.color.primayApp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.outline_home_black_18dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.outline_favorite_border_white_18dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.baseline_access_time_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.outline_person_white_18dp));
        spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.primayApp));
        spaceNavigationView.setInActiveSpaceItemColor(ContextCompat.getColor(this, R.color.gray));
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                fragment = new AddInfoRoomFragment();
                loadFragment(fragment);
                spaceNavigationView.setVisibility(View.GONE);
            }
            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Log.d("AAA",itemIndex+"");
                switch (itemIndex){
                    case  0:
                        fragment = new DetailRoomFragment();
                        loadFragment(fragment);
                        break;
                    case 1:
                        fragment = new FavoriteFragment();
                        loadFragment(fragment);
                        break;
                    case  2:
                        fragment = new MapFragment();
                        loadFragment(fragment);
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });
    }
    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame, fragment).commit();
            return true;
        }
        return false;
    }
}