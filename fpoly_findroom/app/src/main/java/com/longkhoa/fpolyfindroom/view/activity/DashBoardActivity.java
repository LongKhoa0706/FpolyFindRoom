package com.longkhoa.fpolyfindroom.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Home;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.room.AddInfoRoomFragment;
import com.longkhoa.fpolyfindroom.view.map.MapFragment;
import com.longkhoa.fpolyfindroom.view.profile.ProfileFragment;
import com.longkhoa.fpolyfindroom.view.favorite.FavoriteFragment;
import com.longkhoa.fpolyfindroom.view.home.HomeFragment;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class DashBoardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public  static BottomNavigationView bottomNavigationMenuView;
    private static final String TAG = MainActivity.class.getSimpleName() ;
    FragmentManager manager;
    Fragment fragment= null;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        checkLocationPermission();

        bottomNavigationMenuView = findViewById(R.id.bottomBar);
        bottomNavigationMenuView.setOnNavigationItemSelectedListener(this);

    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(DashBoardActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                // goi lại khi user nhấn từ chối quyền location
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,
                                2000, mLocationListener);

                    }

                } else {
                    Log.d("PERMISONN","FALSE");
                    new SweetAlertDialog(DashBoardActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Dịch vụ vị trí")
                            .setContentText("Vui lòng cho phép Findroom sử dụng dịch vụ vị trí để giúp bạn tìm địa điểm cho thuê phù hợp nhất ")
                            .setConfirmText("Có")
                            .setConfirmButtonBackgroundColor(ContextCompat.getColor(DashBoardActivity.this,R.color.primayApp)).
                            setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    checkLocationPermission();

                                }
                            })
                            .setCancelButton("Không", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    finish();
                                    System.exit(0);
                                }
                            })
                            .show();
                }
                return;
            }

        }
    }


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }

        @Override
        public void onLocationChanged(final Location location) {
            SharedPreferences sharedPref = getApplication().getSharedPreferences(Constant.KEY_LOCATION, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("latitude", Double.valueOf(location.getLatitude()).toString());
            editor.putString("longitude", Double.valueOf(location.getLongitude()).toString());
            editor.commit();
        }
    };

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeItem:
                fragment = new HomeFragment();
                loadFragment(fragment);
                break;
            case R.id.favoriteItem:
                fragment = new FavoriteFragment();
                loadFragment(fragment);
                break;
            case R.id.mapItem:
                fragment = new MapFragment();
                loadFragment(fragment);
                break;
            case R.id.profileItem:
                fragment = new ProfileFragment();
                loadFragment(fragment);
                break;
        }
        return true;
    }
}