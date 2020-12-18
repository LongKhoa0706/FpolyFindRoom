package com.longkhoa.fpolyfindroom.view.room;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.longkhoa.fpolyfindroom.R;

import com.longkhoa.fpolyfindroom.adapter.ChooseImageAdapter;
import com.longkhoa.fpolyfindroom.adapter.SlideImageAdapter;
import com.longkhoa.fpolyfindroom.adapter.SpinnerLocationAdapter;
import com.longkhoa.fpolyfindroom.model.City;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.presenter.location.LocationInterface;
import com.longkhoa.fpolyfindroom.presenter.location.LocationPresenter;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.BottomSheet;
import com.longkhoa.fpolyfindroom.view.ChooseImageFragment;
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

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;


public class AddInfoRoomFragment extends Fragment implements OnMapReadyCallback, LocationInterface {
    MaterialSpinner spinnerChooseType;
    AppCompatSpinner spinnerCity,spinnerDistrict,spinnerWard;
    Button btnCreateRoom;
    SlideImageAdapter slideImageAdapter;
    ArrayList<String> arrayListImage = new ArrayList<>();
    RecyclerView recyclerViewDisplayImage;
    ImageView iconChooseImage;
    CarmenFeature feature;
    SpinnerLocationAdapter spinnerLocationAdapter;
    private String geojsonSourceLayerId = "geojsonSourceLayerId";
    private String symbolIconId = "symbolIconId";
    int REQUEST_CODE_AUTOCOMPLETE = 4;
    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_PickImage = 987;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    TextView txtSetupLocation;
    LocationPresenter locationPresenter;
    private MapView mapView;
    private EditText edtTitle, edtSearch;
    String type = "";
    List<String> listImageUrl = new ArrayList<>();
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
        iconChooseImage = view.findViewById(R.id.iconChooseImage);
        edtTitle = view.findViewById(R.id.edtTitle);
        recyclerViewDisplayImage = view.findViewById(R.id.reyclerViewDisplayImage);
        spinnerCity = view.findViewById(R.id.spinnerCity);
        spinnerChooseType = view.findViewById(R.id.spinner);
        btnCreateRoom = view.findViewById(R.id.btnAddRoom);
        mapView = view.findViewById(R.id.mapBoxAddInfo);

//        imgChooseImage = view.findViewById(R.id.imgChooseImage);
        spinnerDistrict = view.findViewById(R.id.spinnerDistrict);
        spinnerWard = view.findViewById(R.id.spinnerWard);
        iconChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForderPermission();
            }
        });


        locationPresenter = new LocationPresenter(this);
        locationPresenter.createLocation();
        txtSetupLocation = view.findViewById(R.id.txtSetupLocation);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

      edtSearch.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
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
                type = item;

            }
        });
        btnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    Room room = new Room(arrayListImage,false,null,null,type,
                            edtSearch.getText().toString(),
                            "",0,edtTitle.getText().toString(),
                            null,null,null,((Point) feature.geometry()).latitude(),((Point) feature.geometry()).longitude(),0,0,0,0);
                    bundle.putParcelable("room",room);
                    CreateRoomFragment createRoomFragment = new CreateRoomFragment();
                    createRoomFragment.setArguments(bundle);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame,createRoomFragment).commit();

//                BottomSheet bottomSheet = new BottomSheet();
//                bottomSheet.show(getFragmentManager(), "bottomSheet");
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
             feature = PlaceAutocomplete.getPlace(data);
            edtSearch.setText(feature.placeName());

            if (mapbox != null) {
                Style style = mapbox.getStyle();
                if (style != null) {
                    GeoJsonSource source = style.getSourceAs(geojsonSourceLayerId);
                    if (source != null) {
                        source.setGeoJson(FeatureCollection.fromFeatures(new Feature[]{Feature.fromJson(feature.toJson())}));
                    }
                    mapbox.animateCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(((Point) feature.geometry()).latitude(), ((Point) feature.geometry()).longitude()))
                                    .zoom(14)
                                    .build()), 4000);

                }

            }
        }

        if (requestCode == REQUEST_CODE_PickImage && resultCode == RESULT_OK && data != null){
            if (data.getClipData() != null){
                int count = data.getClipData().getItemCount();
                for (int i = 0 ; i<count;i++){
                    final ProgressDialog dialog = new ProgressDialog(getActivity());
                    dialog.setTitle("Đang tải hình.....");
                    dialog.show();
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                     String image = getFileName(imageUri);
                    storageReference = FirebaseStorage.getInstance().getReference(image);
                    UploadTask uploadTask = storageReference.putFile(imageUri);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    dialog.dismiss();
                                    String abc = uri.toString();
//                                    Log.d("LINK",abc);
                                    arrayListImage.add(abc);
                                    recyclerViewDisplayImage.setHasFixedSize(true);
                                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2,RecyclerView.VERTICAL,false);
                                    recyclerViewDisplayImage.setLayoutManager(gridLayoutManager);
                                    slideImageAdapter = new SlideImageAdapter(getActivity(),arrayListImage,R.layout.custom_choose_image);
                                    recyclerViewDisplayImage.setAdapter(slideImageAdapter);
                                    ViewGroup.LayoutParams params=recyclerViewDisplayImage.getLayoutParams();
                                    params.height=800;
                                    recyclerViewDisplayImage.setLayoutParams(params);
                                }
                            });
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                            dialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
                }

            }else {

            }

        };

    }

    private void checkForderPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG,"Permission not available requesting permission");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PickImage);

        } else {
            Intent photoPickerIntent = new Intent();
            photoPickerIntent.setType("image/*");
            photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(photoPickerIntent,"Select Image(s)"),REQUEST_CODE_PickImage);
        }
    }

    private void setUpSource(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource(geojsonSourceLayerId));
    }


    @Override
    public void getCity(List<City> listCity) {

        spinnerLocationAdapter = new SpinnerLocationAdapter(listCity,getActivity(),R.layout.custom_item_location);
        spinnerCity.setAdapter(spinnerLocationAdapter);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerDistrict.setVisibility(View.VISIBLE);
                locationPresenter.getDistrictByCity(i+1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void getDistrictByCity(List<City> listCity) {
        spinnerLocationAdapter = new SpinnerLocationAdapter(listCity,getActivity(),R.layout.custom_item_location);
        spinnerDistrict.setAdapter(spinnerLocationAdapter);
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerDistrict.setVisibility(View.VISIBLE);
                locationPresenter.getWardByDistrict(listCity.get(i).getID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void getWardByDistrict(List<City> listCity) {
        spinnerLocationAdapter = new SpinnerLocationAdapter(listCity,getActivity(),R.layout.custom_item_location);
        spinnerWard.setAdapter(spinnerLocationAdapter);
        spinnerWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Log.d("DDD","permission denied! Disable the function related with permission.");
                }
            }
            break;
            case REQUEST_CODE_PickImage: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Log.d("DDD","permission denied! Disable the function related with permission.");
                }
                break;
            }
        }
    }



}
