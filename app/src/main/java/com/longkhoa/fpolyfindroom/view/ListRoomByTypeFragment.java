package com.longkhoa.fpolyfindroom.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.presenter.room.getroombytype.GetRoomByTypeInterface;
import com.longkhoa.fpolyfindroom.presenter.room.getroombytype.GetRoomByTypePresenter;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;


public class ListRoomByTypeFragment extends Fragment implements GetRoomByTypeInterface {
    GetRoomByTypePresenter getRoomByTypePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_room_by_type, container, false);
        DashBoardActivity.bottomNavigationMenuView.setVisibility(View.GONE);
        getRoomByTypePresenter = new GetRoomByTypePresenter(this);
        Bundle bundle = getArguments()4;
        if (bundle !=null){
            String type = bundle.getString("type");
            getRoomByTypePresenter.getListRoomByType(type);
        }
        return view;
    }

    @Override
    public void getRoomByType(MyStatusRoom myStatusRoom) {

    }
}