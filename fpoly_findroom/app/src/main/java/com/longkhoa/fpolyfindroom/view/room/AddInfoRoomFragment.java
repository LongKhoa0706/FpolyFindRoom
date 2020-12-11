package com.longkhoa.fpolyfindroom.view.room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.BottomSheet;

public class AddInfoRoomFragment extends Fragment implements OnMapReadyCallback {
    MaterialSpinner spinnerChooseType;
    Button btnCreateRoom;
    GoogleMap googleMapp;
    double latitude;
    double longitude;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addroominfo_fragment,container, false);
        spinnerChooseType = view.findViewById(R.id.spinner);
        btnCreateRoom = view.findViewById(R.id.btnAddRoom);
        spinnerChooseType.setItems("Nhà", "Căn hộ", "Phòng");
        spinnerChooseType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        btnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getFragmentManager(),"bottomSheet");
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googlemapInfroFragment);
        if (mapFragment!=null){
            mapFragment.getMapAsync(this);
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener
                    Log.d("kasdasdsakd","VOAOO");
                    return true;
                }
                Log.d("kasdasdsakd","VOAOO");

                return false;
            }
        });}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapp = googleMap;
        SharedPreferences sharedPref = getActivity().getSharedPreferences(Constant.KEY_LOCATION, getActivity().MODE_PRIVATE);
        latitude = Double.parseDouble(sharedPref.getString("latitude",""));
        longitude = Double.parseDouble(sharedPref.getString("longitude",""));
        LatLng latLng = new LatLng(latitude, longitude);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng )      // Sets the center of the map to Mountain View
                .zoom(16f)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        googleMapp.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }
}
