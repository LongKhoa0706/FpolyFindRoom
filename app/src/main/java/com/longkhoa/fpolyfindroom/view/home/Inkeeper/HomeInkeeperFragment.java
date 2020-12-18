package com.longkhoa.fpolyfindroom.view.home.Inkeeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.presenter.room.listmyroom.ListMyRoomInterface;
import com.longkhoa.fpolyfindroom.presenter.room.listmyroom.ListMyRoomPresenter;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;
import com.longkhoa.fpolyfindroom.view.room.AddInfoRoomFragment;

public class HomeInkeeperFragment extends Fragment implements ListMyRoomInterface {
    ListMyRoomPresenter listMyRoomPresenter;
    RecyclerView recyclerViewInkeeper;
    Button btnRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_inkeeper_fragment, container, false);
        recyclerViewInkeeper = view.findViewById(R.id.reyclerViewManageRoom);
        btnRoom = view.findViewById(R.id.btnRoom);
        listMyRoomPresenter = new ListMyRoomPresenter(this);
        listMyRoomPresenter.getListMyRoom();
        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.dashboard_frame, new AddInfoRoomFragment()).commit();
                DashBoardActivity.bottomNavigationMenuView.setVisibility(View.GONE);
            }
        });

        return view;

    }


    @Override
    public void getListMyRooms(MyStatusRoom myStatusRoom) {

    }
}

