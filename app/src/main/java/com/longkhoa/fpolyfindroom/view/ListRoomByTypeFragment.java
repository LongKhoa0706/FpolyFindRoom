package com.longkhoa.fpolyfindroom.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.HomeInAdapter;
import com.longkhoa.fpolyfindroom.adapter.ListByTypeAdapter;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.presenter.room.getroombytype.GetRoomByTypeInterface;
import com.longkhoa.fpolyfindroom.presenter.room.getroombytype.GetRoomByTypePresenter;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;
import com.longkhoa.fpolyfindroom.view.room.DetailRoomFragment;

import java.util.ArrayList;


public class ListRoomByTypeFragment extends Fragment implements GetRoomByTypeInterface, CallbackRoomAdapter {
    GetRoomByTypePresenter getRoomByTypePresenter;
    ListByTypeAdapter listByTypeAdapter;
    ProgressBar progressBar;
    RecyclerView recyclerViewTypeRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_room_by_type, container, false);
        progressBar = view.findViewById(R.id.progressBarListByType);
        recyclerViewTypeRoom = view.findViewById(R.id.reyclerViewTypeRoom);
        progressBar.setIndeterminateDrawable(new ChromeFloatingCirclesDrawable.Builder(getActivity())
                .build());


        DashBoardActivity.bottomNavigationMenuView.setVisibility(View.GONE);
        getRoomByTypePresenter = new GetRoomByTypePresenter(this);
        Bundle bundle = getArguments();
        if (bundle !=null){
            String type = bundle.getString("type");
            getRoomByTypePresenter.getListRoomByType(type);
        }
        return view;
    }

    @Override
    public void getRoomByType(MyStatusRoom myStatusRoom) {
        progressBar.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManagerHorizone = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewTypeRoom.setLayoutManager(linearLayoutManagerHorizone);
        listByTypeAdapter = new ListByTypeAdapter(myStatusRoom.getData(),getActivity(),R.layout.custom_item_list_room_type,this);
        recyclerViewTypeRoom.setAdapter(listByTypeAdapter);
        listByTypeAdapter.notifyDataSetChanged();

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