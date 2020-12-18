package com.longkhoa.fpolyfindroom.view.home.Inkeeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.HomeInAdapter;
import com.longkhoa.fpolyfindroom.adapter.HomeRoomAdapter;

import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.presenter.room.listmyroom.ListMyRoomInterface;
import com.longkhoa.fpolyfindroom.presenter.room.listmyroom.ListMyRoomPresenter;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;
import com.longkhoa.fpolyfindroom.view.room.AddInfoRoomFragment;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;
import com.longkhoa.fpolyfindroom.view.room.DetailRoomFragment;

public class HomeInkeeperFragment extends Fragment implements ListMyRoomInterface, CallbackRoomAdapter {
    HomeInAdapter homeInAdapter;
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
        LinearLayoutManager linearLayoutManagerHorizone = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewInkeeper.setLayoutManager(linearLayoutManagerHorizone);
        homeInAdapter = new HomeInAdapter(getActivity(), myStatusRoom.getData(), R.layout.custom_item_room,this);
        recyclerViewInkeeper.setAdapter(homeInAdapter);
    }

    @Override
    public void onClickListenerCardView(Room room) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_ROOM,room);
        DetailRoomFragment detailRoomFragment = new DetailRoomFragment();
        detailRoomFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame, detailRoomFragment).addToBackStack(null).commit();
    }
}

