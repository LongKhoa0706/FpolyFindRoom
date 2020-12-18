package com.longkhoa.fpolyfindroom.view.room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.SlideImageAdapter;
import com.longkhoa.fpolyfindroom.adapter.UtilAdapter;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.model.Util;
import com.longkhoa.fpolyfindroom.view.ChooseImageFragment;
import com.longkhoa.fpolyfindroom.view.util.ListUtilFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CreateRoomFragment extends Fragment {
    LinearLayout linearAddUtil;
    ImageSlider slider;
    UtilAdapter utilAdapter;
    RecyclerView reyclerViewDisplayUtil;
    final int REQUEST_CODE = 1;
    TextView txtType, txtTitle, txtAddress;
    LinearLayout linearLayout;
    Room room;
    List<SlideModel> listImage = new ArrayList<SlideModel>();
    EditText edtPrice,edtBed,edtShower,edtAmountRoom,edtDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_room, container, false);
        linearAddUtil = view.findViewById(R.id.linearAddUtil);
        edtPrice = view.findViewById(R.id.edtPriceCreateRoom);
        edtBed = view.findViewById(R.id.edtBedCreateRoom);
        edtShower = view.findViewById(R.id.edtShowerCreateRoom);
        edtAmountRoom = view.findViewById(R.id.edtAmountacreage);
        edtDescription = view.findViewById(R.id.edtDescCreateRoom);
        txtType = view.findViewById(R.id.txtTypeCreateRoom);
        txtTitle = view.findViewById(R.id.txtTitleCreateRoom);
        reyclerViewDisplayUtil = view.findViewById(R.id.reyclerViewDisplayUtil);
        txtAddress = view.findViewById(R.id.txtAddressCreateRoom);
        slider = view.findViewById(R.id.image_slider);
        linearLayout = view.findViewById(R.id.linear);

        Bundle bundle = getArguments();
        if (bundle != null) {
            room = bundle.getParcelable("room");
            room.getImage().forEach((e) -> {
                listImage.add(new SlideModel(e, ScaleTypes.CENTER_CROP));
                slider.setImageList(listImage);
            });
            slider.startSliding(1500);
            txtType.setText(room.getType());
            txtAddress.setText(room.getLocation());
            txtTitle.setText(room.getTitle());
        } else {

        }

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("utils", 0);
        if (sharedPreferences != null) {
            String util = sharedPreferences.getString("utils", "");
            ArrayList<Util> arrayList = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(util);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    int icon = jsonObject.getInt("icon");
                    Util util1 = new Util(title, icon);
                    arrayList.add(util1);
                    reyclerViewDisplayUtil.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                    reyclerViewDisplayUtil.setLayoutManager(layoutManager);
                    utilAdapter = new UtilAdapter(arrayList, getActivity(), R.layout.custom_item_util, null);
                    reyclerViewDisplayUtil.setAdapter(utilAdapter);
                    ViewGroup.LayoutParams params=reyclerViewDisplayUtil.getLayoutParams();
                    params.height=600;
                    reyclerViewDisplayUtil.setLayoutParams(params);
                    utilAdapter.notifyDataSetChanged();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

//
        }
        linearAddUtil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListUtilFragment listUtilFragment = new ListUtilFragment();
                getFragmentManager().beginTransaction().replace(R.id.dashboard_frame, listUtilFragment).addToBackStack(null).commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}