package com.longkhoa.fpolyfindroom.view.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.UtilAdapter;
import com.longkhoa.fpolyfindroom.model.Util;
import com.longkhoa.fpolyfindroom.view.activity.ClientActivity;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;

import java.util.ArrayList;

public class ListUtilFragment extends Fragment implements CallBackUtilAdapter {
    RecyclerView recyclerViewUtil;
    ArrayList<Util>arrUtil = new ArrayList<>();
    ArrayList<Util>arrSaveUtil = new ArrayList<>();
    UtilAdapter utilAdapter;
    MaterialToolbar toolbarUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_util, container, false);
        recyclerViewUtil = view.findViewById(R.id.reyclerViewUtil);
        toolbarUtil = view.findViewById(R.id.toolbarUtil);
        ((DashBoardActivity)getActivity()).setSupportActionBar(toolbarUtil);
        setHasOptionsMenu(true);


        arrUtil.add(new Util("Phòng cháy chữa cháy ",R.drawable.icon8));
        arrUtil.add(new Util("Phòng cháy chữ ",R.drawable.icon2));
        arrUtil.add(new Util("Dịch vụ giặt là ",R.drawable.icon3));
        arrUtil.add(new Util("Ban công",R.drawable.icon4));
        arrUtil.add(new Util("Thân thiện trẻ",R.drawable.icon5));

        arrUtil.add(new Util("Cctv",R.drawable.icon7));
        arrUtil.add(new Util("Thang máy",R.drawable.icon9));
        arrUtil.add(new Util("Nuôi thú cưng",R.drawable.icon10));
        arrUtil.add(new Util("Bảo vệ 24/7",R.drawable.icon11));
        arrUtil.add(new Util("WIFI",R.drawable.icon12));
        arrUtil.add(new Util("WIFI tốc độ cao",R.drawable.icon13));
        arrUtil.add(new Util("Gym",R.drawable.icon14));
        arrUtil.add(new Util("Đầy đủ nội thất",R.drawable.icon15));
        arrUtil.add(new Util("Thẻ định danh",R.drawable.icon16));
        arrUtil.add(new Util("Gác lửng",R.drawable.icon17));
        arrUtil.add(new Util("Chỗ giữ xe",R.drawable.icon18));
        arrUtil.add(new Util("Lễ tân",R.drawable.icon19));
        recyclerViewUtil.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerViewUtil.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUtil.setLayoutManager(linearLayoutManager);
        utilAdapter = new UtilAdapter(arrUtil,getActivity(),R.layout.custom_item_util,this);
        recyclerViewUtil.setAdapter(utilAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_util,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveItem:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("utils",0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String jsonText = gson.toJson(arrSaveUtil);
                editor.putString("utils", jsonText);
                editor.apply();
                getActivity().onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickListenerCardView(Util util) {
        arrSaveUtil.add(util);

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//                util.setCheck(true);
//                Gson gson = new Gson();
//                String jsonText = gson.toJson(util);
//                editor.putString("utils", jsonText);
//                editor.apply();//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ultil", Context.MODE_PRIVATE);

    }
}