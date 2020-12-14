package com.longkhoa.fpolyfindroom.view.home.Inkeeper;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;
import com.longkhoa.fpolyfindroom.view.room.AddInfoRoomFragment;

public class HomeInkeeperFragment extends Fragment {
    RecyclerView recyclerViewInkeeper;
    Button btnRoom;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_inkeeper_fragment, container, false);
        recyclerViewInkeeper = view.findViewById(R.id.reyclerViewManageRoom);
        btnRoom = view.findViewById(R.id.btnRoom);

        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.dashboard_frame, new AddInfoRoomFragment()).commit();
                DashBoardActivity.bottomNavigationMenuView.setVisibility(View.GONE);
            }
        });

        return view;

    }


}

