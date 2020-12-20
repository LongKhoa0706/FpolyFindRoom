package com.longkhoa.fpolyfindroom.view.room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.textfield.TextInputLayout;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.UtilAdapter;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.model.Util;
import com.longkhoa.fpolyfindroom.presenter.room.addroom.AddRoomInterface;
import com.longkhoa.fpolyfindroom.presenter.room.addroom.AddRoomPresenter;
import com.longkhoa.fpolyfindroom.presenter.room.updateroom.UpdateRoomPresenter;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;
import com.longkhoa.fpolyfindroom.view.home.Inkeeper.HomeInkeeperFragment;
import com.longkhoa.fpolyfindroom.view.util.ListUtilFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CreateRoomFragment extends Fragment  implements AddRoomInterface {
    LinearLayout linearAddUtil;
    ImageSlider slider;
    UtilAdapter utilAdapter;
    UpdateRoomPresenter updateRoomPresenter;
    RecyclerView reyclerViewDisplayUtil;
    AddRoomPresenter addRoomPresenter;
    final int REQUEST_CODE = 1;
    int option =0;

    TextView txtType, txtTitle, txtAddress;
    LinearLayout linearLayout;
    Room room;
    Room room1;
    ArrayList<Util> arrayListUtil = new ArrayList<>();
    List<SlideModel> listImage = new ArrayList<SlideModel>();
    TextInputLayout edtPrice,edtBed,edtShower,edtAmountRoom,edtDescription,edtAcreage;
    AppCompatButton btnSaveRoom;
    ArrayList<String> arrList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_room, container, false);
        linearAddUtil = view.findViewById(R.id.linearAddUtil);
        edtPrice = view.findViewById(R.id.edtPriceCreateRoom);
        edtBed = view.findViewById(R.id.edtBedCreateRoom);
        edtShower = view.findViewById(R.id.edtShowerCreateRoom);
        edtAmountRoom = view.findViewById(R.id.edtAmountacreage);
        edtDescription = view.findViewById(R.id.edtDescCreateRoom);
        edtAcreage = view.findViewById(R.id.edtEcreage);
        btnSaveRoom = view.findViewById(R.id.btnSaveRoom);
        updateRoomPresenter = new UpdateRoomPresenter();
        txtType = view.findViewById(R.id.txtTypeCreateRoom);
        txtTitle = view.findViewById(R.id.txtTitleCreateRoom);
        reyclerViewDisplayUtil = view.findViewById(R.id.reyclerViewDisplayUtil);
        txtAddress = view.findViewById(R.id.txtAddressCreateRoom);
        slider = view.findViewById(R.id.image_slider);
        linearLayout = view.findViewById(R.id.linear);
        DashBoardActivity.bottomNavigationMenuView.setVisibility(View.GONE);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("utils", 0);
        addRoomPresenter = new AddRoomPresenter(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            option = bundle.getInt("option");
            switch (option){
                case 0:
                    room = bundle.getParcelable("room");
                    room.getImage().forEach((e) -> {
                        listImage.add(new SlideModel(e, ScaleTypes.CENTER_CROP));
                        slider.setImageList(listImage);
                    });
                    slider.startSliding(1500);
                    txtType.setText(room.getType());
                    txtAddress.setText(room.getLocation());
                    txtTitle.setText(room.getTitle());

                    btnSaveRoom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Room saveRoom = new Room(room.getImage(),
                                    false,arrList,null,
                                    room.getType(),room.getLocation(),
                                    edtDescription.getEditText().getText().toString(),
                                    Integer.parseInt(edtPrice.getEditText().getText().toString()),
                                    room.getTitle(),null,null,null,
                                    room.getLat(),room.getLng(), Integer.parseInt(edtAmountRoom.getEditText().getText().toString()),
                                    Integer.parseInt(edtShower.getEditText().getText().toString()),Integer.parseInt(edtBed.getEditText().getText().toString()),
                                    Integer.parseInt(edtAcreage.getEditText().getText().toString()));

                            addRoomPresenter.createRoom(saveRoom,DashBoardActivity.Token);
                            getActivity().getSharedPreferences("utils",0).edit().clear().apply();
                        }
                    });
                    break;
                case 1:
                    btnSaveRoom.setText("Thay đổi");
                    room = bundle.getParcelable("room1");
                    room.getImage().forEach((e) -> {
                        listImage.add(new SlideModel(e, ScaleTypes.CENTER_CROP));
                        slider.setImageList(listImage);
                    });
                    slider.startSliding(1500);
                    txtType.setText(room.getType());
                    txtAddress.setText(room.getLocation());
                    txtTitle.setText(room.getTitle());

                    edtPrice.getEditText().setText(room.getPrice()+"");
                    edtAmountRoom.getEditText().setText(room.getAmount_room()+"");
                    edtAcreage.getEditText().setText(room.getAcreage()+"");
                    edtDescription.getEditText().setText(room.getDescription()+"");
                    edtShower.getEditText().setText(room.getAmount_bathroom()+"");
                    edtBed.getEditText().setText(room.getAmount_bedroom()+"");


                    btnSaveRoom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            updateRoomPresenter.updateRoom(room.getId(),Integer.parseInt(edtPrice.getEditText().getText().toString()),DashBoardActivity.Token);
                            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.dashboard_frame,new HomeInkeeperFragment()).commit();

//                            Room saveRoom = new Room(room.getImage(),
//                                    false,arrList,null,
//                                    room.getType(),room.getLocation(),
//                                    edtDescription.getEditText().getText().toString(),
//                                    Integer.parseInt(edtPrice.getEditText().getText().toString()),
//                                    room.getTitle(),null,null,null,
//                                    room.getLat(),room.getLng(), Integer.parseInt(edtAmountRoom.getEditText().getText().toString()),
//                                    Integer.parseInt(edtShower.getEditText().getText().toString()),Integer.parseInt(edtBed.getEditText().getText().toString()),
//                                    Integer.parseInt(edtAcreage.getEditText().getText().toString()));
//
//                            addRoomPresenter.createRoom(saveRoom);
//                            getActivity().getSharedPreferences("utils",0).edit().clear().apply();
                        }
                    });
                    break;

            }
        } else {

        }


        if (sharedPreferences != null) {
            String util = sharedPreferences.getString("utils", "");
            try {
                JSONArray jsonArray = new JSONArray(util);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    int icon = jsonObject.getInt("icon");
                    Util util1 = new Util(title, icon);
                    arrayListUtil.add(util1);
                    reyclerViewDisplayUtil.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                    reyclerViewDisplayUtil.setLayoutManager(layoutManager);
                    utilAdapter = new UtilAdapter(arrayListUtil, getActivity(), R.layout.custom_item_util, null);
                    reyclerViewDisplayUtil.setAdapter(utilAdapter);
                    arrayListUtil.forEach((e)->{
                        String total = String.valueOf(e.getIcon()) +" "+ e.getTitle() ;
                        arrList.add(total);
                    });
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

    @Override
    public void creteRoom() {

    }
}