package com.longkhoa.fpolyfindroom.view.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;

import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private MapboxMap mapboxMapp;
    double latitude;
    EditText edtSearch;
    double longitude;
    private PermissionsManager permissionsManager;
    int REQUEST_CODE_AUTOCOMPLETE = 4;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mapbox.getInstance(getActivity(), "pk.eyJ1IjoibG9uZ2tob2EwNzA2IiwiYSI6ImNraWswdTR3YzA1OGwzMmw5bHhrbHZocnUifQ.GEKhu3FRz7wmLV2E-0KG5A");
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        mapView = view.findViewById(R.id.mapBoxFragment);
        edtSearch = view.findViewById(R.id.edtSearch);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken("pk.eyJ1IjoibG9uZ2tob2EwNzA2IiwiYSI6ImNraWswdTR3YzA1OGwzMmw5bHhrbHZocnUifQ.GEKhu3FRz7wmLV2E-0KG5A")
                        .placeOptions(PlaceOptions.builder().country("VN").backgroundColor(Color.parseColor("#FFFFFFFF")).build())
                        .build(getActivity());
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
            }
        });

        return view;
    }


    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.KEY_LOCATION, Context.MODE_PRIVATE);
        latitude = Double.parseDouble(sharedPreferences.getString("latitude", ""));
        longitude = Double.parseDouble(sharedPreferences.getString("longitude", ""));


        mapboxMapp = mapboxMap;
        mapboxMapp.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(getActivity())

                        .build();
                
                
                LocationComponent locationComponent = mapboxMap.getLocationComponent();

                locationComponent.activateLocationComponent(
                        LocationComponentActivationOptions.builder(getActivity(), style).
                                locationComponentOptions(customLocationComponentOptions).
                                build());

// Enable to make component visible
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationComponent.setLocationComponentEnabled(true);

// Set the component's camera mode
                locationComponent.setCameraMode(CameraMode.NONE);

// Set the component's render mode
                locationComponent.setRenderMode(RenderMode.NORMAL);


                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(latitude, longitude)) // Sets the new camera position
                        .zoom(14) // Sets the zoom
                        .build(); // Creates a CameraPosition from the builder



                mapboxMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position), 5000);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            CarmenFeature feature = PlaceAutocomplete.getPlace(data);
            Toast.makeText(getActivity(), feature.placeName(), Toast.LENGTH_LONG).show();

        }
    }
}