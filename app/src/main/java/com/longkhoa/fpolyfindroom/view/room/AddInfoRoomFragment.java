package com.longkhoa.fpolyfindroom.view.room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;


import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.longkhoa.fpolyfindroom.R;

import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.BottomSheet;
import com.longkhoa.fpolyfindroom.view.map.MapFragment;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;


public class AddInfoRoomFragment extends Fragment implements OnMapReadyCallback {
    MaterialSpinner spinnerChooseType;
    Button btnCreateRoom;
    private String geojsonSourceLayerId = "geojsonSourceLayerId";
    private String symbolIconId = "symbolIconId";
    int REQUEST_CODE_AUTOCOMPLETE = 4;
    TextView txtSetupLocation;
    private MapView mapView;
    private EditText edtTitle, edtSearch;
    MapboxMap mapbox;
    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String ICON_ID = "ICON_ID";
    private static final String LAYER_ID = "LAYER_ID";

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
        Mapbox.getInstance(getActivity(), "pk.eyJ1IjoibG9uZ2tob2EwNzA2IiwiYSI6ImNraWswdTR3YzA1OGwzMmw5bHhrbHZocnUifQ.GEKhu3FRz7wmLV2E-0KG5A");
        View view = inflater.inflate(R.layout.addroominfo_fragment, container, false);
        edtSearch = view.findViewById(R.id.edtSearchAddress);
        edtTitle = view.findViewById(R.id.edtTitle);
        spinnerChooseType = view.findViewById(R.id.spinner);
        btnCreateRoom = view.findViewById(R.id.btnAddRoom);
        mapView = view.findViewById(R.id.mapBoxAddInfo);

        txtSetupLocation = view.findViewById(R.id.txtSetupLocation);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Intent intent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken("pk.eyJ1IjoibG9uZ2tob2EwNzA2IiwiYSI6ImNraWswdTR3YzA1OGwzMmw5bHhrbHZocnUifQ.GEKhu3FRz7wmLV2E-0KG5A")
                        .placeOptions(PlaceOptions.builder().backgroundColor(Color.parseColor("#FFFFFFFF")).build())
                        .build(getActivity());
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
            }
        });
        spinnerChooseType.setItems("Nhà", "Căn hộ", "Phòng");
        spinnerChooseType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        btnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getFragmentManager(), "bottomSheet");
            }
        });
        txtSetupLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame, new MapFragment()).commit();
            }
        });
        return view;
    }


    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.KEY_LOCATION, Context.MODE_PRIVATE);
        latitude = Double.parseDouble(sharedPreferences.getString("latitude", ""));
        longitude = Double.parseDouble(sharedPreferences.getString("longitude", ""));

        mapbox = mapboxMap;
        mapbox.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_location_on_red_500_24dp, null);
                Bitmap mBitmap = BitmapUtils.getBitmapFromDrawable(drawable);
                style.addImage(symbolIconId, mBitmap );

// Create an empty GeoJSON source using the empty feature collection
                setUpSource(style);

// Set up a new symbol layer for displaying the searched location's feature coordinates
                setupLayer(style);
            }
        });

//        List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(
//                Point.fromLngLat(-57.225365, -33.213144)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(
//                Point.fromLngLat(-54.14164, -33.981818)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(
//                Point.fromLngLat(-56.990533, -30.583266)));

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)) // Sets the new camera position
                .zoom(17) // Sets the zoom
                .build();
        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);

    }


    private void setupLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addLayer(new SymbolLayer("SYMBOL_LAYER_ID", geojsonSourceLayerId).withProperties(
                iconImage(symbolIconId),
                iconOffset(new Float[]{0f, -8f})
        ));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            CarmenFeature feature = PlaceAutocomplete.getPlace(data);


            if (mapbox != null) {
                Style style = mapbox.getStyle();
                if (style != null) {
                    GeoJsonSource source = style.getSourceAs(geojsonSourceLayerId);
                    if (source != null) {
                        source.setGeoJson(FeatureCollection.fromFeatures(
                                new Feature[]{Feature.fromJson(feature.toJson())}));
                    }

// Move map camera to the selected location
                    mapbox.animateCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(((Point) feature.geometry()).latitude(),
                                            ((Point) feature.geometry()).longitude()))
                                    .zoom(14)
                                    .build()), 4000);
                }

            }
        }
    }

    private void setUpSource(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource(geojsonSourceLayerId));
    }

}
