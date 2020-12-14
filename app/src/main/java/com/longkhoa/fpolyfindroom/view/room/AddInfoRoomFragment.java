package com.longkhoa.fpolyfindroom.view.room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.longkhoa.fpolyfindroom.R;

import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.BottomSheet;
import com.mapbox.mapboxsdk.Mapbox;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;


public class AddInfoRoomFragment extends Fragment implements OnMapReadyCallback {
    MaterialSpinner spinnerChooseType;
    Button btnCreateRoom;
    private MapView mapView;
    MapboxMap mapbox;

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    //    GoogleMap googleMapp;
    double latitude;
    double longitude;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mapbox.getInstance(getActivity(),"pk.eyJ1IjoibG9uZ2tob2EwNzA2IiwiYSI6ImNraWswdTR3YzA1OGwzMmw5bHhrbHZocnUifQ.GEKhu3FRz7wmLV2E-0KG5A");
        View view = inflater.inflate(R.layout.addroominfo_fragment,container, false);
        spinnerChooseType = view.findViewById(R.id.spinner);
        btnCreateRoom = view.findViewById(R.id.btnAddRoom);
        mapView = view.findViewById(R.id.mapBoxAddInfo);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

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
        return view;
    }




    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.KEY_LOCATION, Context.MODE_PRIVATE);
        latitude = Double.parseDouble(sharedPreferences.getString("latitude",""));
        longitude = Double.parseDouble(sharedPreferences.getString("longitude",""));

        mapbox = mapboxMap;
        mapbox.setStyle(Style.OUTDOORS);

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)) // Sets the new camera position
                .zoom(17) // Sets the zoom
                .build(); // Creates a CameraPosition from the builder
        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);

    }
}
