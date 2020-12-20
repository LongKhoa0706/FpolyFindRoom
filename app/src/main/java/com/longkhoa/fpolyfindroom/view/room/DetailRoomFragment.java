package com.longkhoa.fpolyfindroom.view.room;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.UtilDetailRoomAdapter;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;
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
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class DetailRoomFragment extends Fragment implements OnMapReadyCallback {
    private Room room;
    TextView txtType,txtTitle,txtPrice,txtDate,txtDesc,txtLocation,txtAuthor,txtPhone;
    ImageView imgIconType;
    CircleImageView circleImageView;
    CircleImageView imgAuthor;
    UtilDetailRoomAdapter utilDetailRoomAdapter;
    private String geojsonSourceLayerId = "geojsonSourceLayerId";
    private String symbolIconId = "symbolIconId";
    RecyclerView recyclerViewUtil;
    MapboxMap mapbox;
    private MapView mapView;
    CarouselView carouselView;
    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String ICON_ID = "ICON_ID";
    private static final String LAYER_ID = "LAYER_ID";

    double lat;
    double lng;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mapbox.getInstance(getActivity(), "pk.eyJ1IjoibG9uZ2tob2EwNzA2IiwiYSI6ImNraWswdTR3YzA1OGwzMmw5bHhrbHZocnUifQ.GEKhu3FRz7wmLV2E-0KG5A");

        View view = inflater.inflate(R.layout.detailroom_fragment, container, false);
        txtType = view.findViewById(R.id.txtTypeDetailRoom);
        carouselView = view.findViewById(R.id.carouselView);
        txtAuthor = view.findViewById(R.id.txtAuthorDetailRoom);
        txtTitle = view.findViewById(R.id.txtTitleDetailRoom);
        circleImageView = view.findViewById(R.id.imgAuthorDetailRoom);
        txtPrice = view.findViewById(R.id.txtPriceDetailRoom);
        mapView = view.findViewById(R.id.mapBoxDetailFragment);
        txtPhone = view.findViewById(R.id.txtPhoneDetailRoom);
        recyclerViewUtil = view.findViewById(R.id.reyclerViewUtilDetailRoom);
        txtDate = view.findViewById(R.id.txtDateDetailRoom);
        txtDesc = view.findViewById(R.id.txtDescDetailRoom);
        imgIconType = view.findViewById(R.id.imgIconDetailRoom);
        txtLocation = view.findViewById(R.id.txtLocationDetailRoom);
        DashBoardActivity.bottomNavigationMenuView.setVisibility(View.GONE);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        Bundle bundle = getArguments();
        room = bundle.getParcelable(Constant.KEY_BUNDLE_ROOM);
        switch (room.getType()){
            case "nhà trọ":
                imgIconType.setImageResource(R.drawable.baseline_home_black_24dp);
                break;
            case "căn hộ":
                imgIconType.setImageResource(R.drawable.baseline_apartment_black_24dp);
                break;
            case "phòng":
                imgIconType.setImageResource(R.drawable.baseline_meeting_room_black_24dp);
                break;
        }
        txtTitle.setText(room.getTitle());
        lat = room.getCoordinates().get(0);
        lng = room.getCoordinates().get(1);
//        Glide.with(getActivity()).load(room.getUser().ge);
        //            txtAuthor.setText(room.getUser().getUserName());
        txtLocation.setText(room.getLocation());
        txtPhone.setText(room.getUser().getPhone());
        txtDesc.setText(room.getDescription());
//        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//           Log.d("AAAA", String.valueOf(formatDate.parse(room.getCreatedAt().substring(0,10))));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        txtPrice.setText(numberFormat(room.getPrice()+"")+"đ");
//        String date = room.getCreatedAt();
//        int from = date.indexOf("T")+1;
//        int to = from+5;
//        String timeOfInterest = date.substring(from, to);
        txtDate.setText(room.getCreatedAt().substring(0,10));

        txtType.setText(room.getType());
        txtAuthor.setText(room.getUser().getName());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerViewUtil.setLayoutManager(gridLayoutManager);
        utilDetailRoomAdapter = new UtilDetailRoomAdapter(room.getUtilities(),getActivity(),R.layout.custom_item_util_detail_room);
        recyclerViewUtil.setAdapter(utilDetailRoomAdapter);
        carouselView.setPageCount(room.getImage().size());
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                Glide.with(getActivity())
                        .load(room.getImage().get(position))
                        .into(imageView);
            }
        });

        return view;
    }

    private void setupLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addLayer(new SymbolLayer("SYMBOL_LAYER_ID", geojsonSourceLayerId).withProperties(
                iconImage(symbolIconId),
                iconOffset(new Float[]{0f, -8f})
        ));
    }

    private void setUpSource(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource(geojsonSourceLayerId));
    }


    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        mapbox = mapboxMap;

        mapbox.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_location_on_red_500_24dp, null);
                Bitmap mBitmap = BitmapUtils.getBitmapFromDrawable(drawable);
                style.addImage(symbolIconId, mBitmap );

                setUpSource(style);

                setupLayer(style);
            }
        });

        List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
        symbolLayerIconFeatureList.add(Feature.fromGeometry(
                Point.fromLngLat(lng, lat)));
        mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41")
                .withImage(ICON_ID, BitmapFactory.decodeResource(
                        getActivity().getResources(), R.drawable.mapbox_marker_icon_default))
                .withSource(new GeoJsonSource(SOURCE_ID,
                        FeatureCollection.fromFeatures(symbolLayerIconFeatureList)))
                .withLayer(new SymbolLayer(LAYER_ID, SOURCE_ID)
                        .withProperties(
                                iconImage(ICON_ID),
                                iconAllowOverlap(true),
                                iconIgnorePlacement(true)
                        )
                ), new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
            }
        });


        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(lat,lng)) // Sets the new camera position
                .zoom(17) // Sets the zoom
                .build();
        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);
    }
    public static String numberFormat(String input) {
        String newString = "";
        DecimalFormat formatter = new DecimalFormat("#,###");
        if (null != input && input.length() > 0) {
            if (input.contains(".") && !input.contains(",")) {
                int endIndex = input.lastIndexOf(".");
                if (endIndex != -1) {
                    newString = formatter.format(Long.parseLong(input.substring(0, endIndex)));
                }
            } else if (input.contains(".") && input.contains(",")) {
                int endIndex = input.lastIndexOf(".");
                newString = input.substring(0, endIndex);
            } else if (input.contains(",") || input.contains(" ")) {
                newString = input;
            } else {
                newString = formatter.format(Long.parseLong(input));
            }
        }
        return newString;
    }

//    public static String formatDate (String date, String initDateFormat, String endDateFormat) throws ParseException {
//        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
//        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
//        String parsedDate = formatter.format(initDate);
//        return parsedDate;
//    }
}

