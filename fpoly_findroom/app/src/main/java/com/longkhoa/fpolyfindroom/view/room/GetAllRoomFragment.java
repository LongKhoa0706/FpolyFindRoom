package com.longkhoa.fpolyfindroom.view.room;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.RoomAdapter;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;

import java.util.ArrayList;


public class GetAllRoomFragment extends Fragment implements CallbackRoomAdapter {
    RecyclerView recyclerViewGetRoom;
    RoomAdapter roomAdapter;
     ArrayList<MyStatusRoom> arrRoom = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_all_room, container, false);
        recyclerViewGetRoom = view.findViewById(R.id.reyclerGetRoom);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewGetRoom.setLayoutManager(layoutManager);
//        Room room = new ;
//        arrRoom.add(new Room("2.500.000đ","Phòng cho thuê","433/Trần Quang Diệu","25-10-2020",null,"Nhà giá rẻ",false,null,"Cho Thue","Quan 7"));
//        arrRoom.add(new Room("2.500.000đ","Phòng cho thuê","433/Trần Quang Diệu","25-10-2020",null,"Nhà giá rẻ",false,null,"Cho Thue","Quan 7"));
//         arrRoom.add(new Room("2.500.000đ","Phòng cho thuê","433/Trần Quang Diệu","25-10-2020",null,"Nhà giá rẻ",false,null,"Cho Thue","Quan 7"));
//        arrRoom.add(new Room("2.500.000đ","Phòng cho thuê","433/Trần Quang Diệu","25-10-2020",null,"Nhà giá rẻ",false,null,"Cho Thue","Quan 7"));
//         arrRoom.add(new Room("2.500.000đ","Phòng cho thuê","433/Trần Quang Diệu","25-10-2020",null,"Nhà giá rẻ",false,null,"Cho Thue","Quan 7"));
//        arrRoom.add(new Room("2.500.000đ","Phòng cho thuê","433/Trần Quang Diệu","25-10-2020",null,"Nhà giá rẻ",false,null,"Cho Thue","Quan 7"));

        roomAdapter = new RoomAdapter(arrRoom,getActivity(),R.layout.custom_item_room,this);
        recyclerViewGetRoom.setAdapter(roomAdapter);
        roomAdapter.notifyDataSetChanged();
        return view;
    }



    @Override
    public void onClickListenerCardView(Room room) {

    }
}