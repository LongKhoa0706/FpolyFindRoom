package com.longkhoa.fpolyfindroom.view.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.BannerAdapter;
import com.longkhoa.fpolyfindroom.adapter.DistrictAdapter;
import com.longkhoa.fpolyfindroom.adapter.HomeAdapter;
import com.longkhoa.fpolyfindroom.adapter.IntroAdapter;
import com.longkhoa.fpolyfindroom.adapter.RoomAdapter;
import com.longkhoa.fpolyfindroom.model.Banner;
import com.longkhoa.fpolyfindroom.model.District;
import com.longkhoa.fpolyfindroom.model.Home;
import com.longkhoa.fpolyfindroom.model.Intro;
import com.longkhoa.fpolyfindroom.model.Room;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //Fragment la oncreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        //district
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_district);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<District> arrayList = new ArrayList<>();
        arrayList.add(new District(R.drawable.ic_baseline_location_on_24,"Gần tôi"));
        arrayList.add(new District(R.drawable.district,"Quận 1"));
        arrayList.add(new District(R.drawable.district,"Quận 2"));
        arrayList.add(new District(R.drawable.district,"Quận 3"));
        arrayList.add(new District(R.drawable.district,"Quận 4"));
        arrayList.add(new District(R.drawable.district,"Quận 5"));
        arrayList.add(new District(R.drawable.district,"Quận 6"));
        arrayList.add(new District(R.drawable.district,"Quận 7"));
        arrayList.add(new District(R.drawable.district,"Quận 8"));
        DistrictAdapter districtAdapter = new DistrictAdapter(arrayList,getActivity());
        recyclerView.setAdapter(districtAdapter);

        //intro
        RecyclerView recyclerView_intro = view.findViewById(R.id.recyclerview_intro);
        LinearLayoutManager linearLayoutManager_intro = new LinearLayoutManager(getActivity());
        linearLayoutManager_intro.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_intro.setLayoutManager(linearLayoutManager_intro);
        recyclerView_intro.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Intro> arrayList_intro = new ArrayList<>();
        arrayList_intro.add(new Intro(R.drawable.intro,"Homestay"));
        arrayList_intro.add(new Intro(R.drawable.intro,"Căn hộ"));
        arrayList_intro.add(new Intro(R.drawable.intro,"Biệt thự"));
        arrayList_intro.add(new Intro(R.drawable.intro,"Nhà nguyên căn"));
        arrayList_intro.add(new Intro(R.drawable.intro,"Kí trúc xá"));
        IntroAdapter introAdapter = new IntroAdapter(arrayList_intro,getActivity());
        recyclerView_intro.setAdapter(introAdapter);

        //banner
        RecyclerView recyclerView_banner = view.findViewById(R.id.recyclerview_banner);
        LinearLayoutManager linearLayoutManager_banner = new LinearLayoutManager(getActivity());
        linearLayoutManager_banner.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_banner.setLayoutManager(linearLayoutManager_banner);
        recyclerView_banner.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Banner> arrayList_banner = new ArrayList<>();
        arrayList_banner.add(new Banner(R.drawable.banner));
        arrayList_banner.add(new Banner(R.drawable.banner));
        arrayList_banner.add(new Banner(R.drawable.banner));
        arrayList_banner.add(new Banner(R.drawable.banner));
        arrayList_banner.add(new Banner(R.drawable.banner));
        BannerAdapter bannerAdapter = new BannerAdapter(arrayList_banner,getActivity());
        recyclerView_banner.setAdapter(bannerAdapter);

        //room
        RecyclerView recyclerView_room = view.findViewById(R.id.recyclerview_room);
        LinearLayoutManager linearLayoutManager_room = new LinearLayoutManager(getActivity());
        linearLayoutManager_room.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_room.setLayoutManager(linearLayoutManager_room);
        recyclerView_room.setItemAnimator(new DefaultItemAnimator());
<<<<<<< HEAD
        ArrayList<Room> arrayList_room = new ArrayList<>();
//        arrayList_room.add(new Room(R.drawable.room,R.drawable.outline_favorite_border_white_24dp,R.drawable.room,"Quận 5, Hồ Chi Minh","bnms","500,000đ","Quận 5, Hồ Chi Minh","bnms","500,000đ"));
//        arrayList_room.add(new Room(R.drawable.room,R.drawable.outline_favorite_border_white_24dp,R.drawable.room,"Quận 5, Hồ Chi Minh","bnms","500,000đ","Quận 5, Hồ Chi Minh","bnms","500,000đ"));
//        arrayList_room.add(new Room(R.drawable.room,R.drawable.outline_favorite_border_white_24dp,R.drawable.room,"Quận 5, Hồ Chi Minh","bnms","500,000đ","Quận 5, Hồ Chi Minh","bnms","500,000đ"));
//        arrayList_room.add(new Room(R.drawable.room,R.drawable.outline_favorite_border_white_24dp,R.drawable.room,"Quận 5, Hồ Chi Minh","bnms","500,000đ","Quận 5, Hồ Chi Minh","bnms","500,000đ"));
//        arrayList_room.add(new Room(R.drawable.room,R.drawable.outline_favorite_border_white_24dp,R.drawable.room,"Quận 5, Hồ Chi Minh","bnms","500,000đ","Quận 5, Hồ Chi Minh","bnms","500,000đ"));
//        RoomAdapter roomAdapter = new RoomAdapter(arrayList_room,getActivity());
//        recyclerView_room.setAdapter(roomAdapter);
=======
        ArrayList<Home> arrayList_room = new ArrayList<>();
        arrayList_room.add(new Home(R.drawable.home,R.drawable.home,R.drawable.outline_favorite_border_white_24dp,R.drawable.outline_favorite_border_white_24dp,"Căn hộ","San's Home","500,000đ","Chung cư","Hồ Chí Minh","500,000đ"));
        arrayList_room.add(new Home(R.drawable.home,R.drawable.home,R.drawable.outline_favorite_border_white_24dp,R.drawable.outline_favorite_border_white_24dp,"Căn hộ","San's Home","500,000đ","Chung cư","Hồ Chí Minh","500,000đ"));
        arrayList_room.add(new Home(R.drawable.home,R.drawable.home,R.drawable.outline_favorite_border_white_24dp,R.drawable.outline_favorite_border_white_24dp,"Căn hộ","San's Home","500,000đ","Chung cư","Hồ Chí Minh","500,000đ"));
        arrayList_room.add(new Home(R.drawable.home,R.drawable.home,R.drawable.outline_favorite_border_white_24dp,R.drawable.outline_favorite_border_white_24dp,"Căn hộ","San's Home","500,000đ","Chung cư","Hồ Chí Minh","500,000đ"));
        arrayList_room.add(new Home(R.drawable.home,R.drawable.home,R.drawable.outline_favorite_border_white_24dp,R.drawable.outline_favorite_border_white_24dp,"Căn hộ","San's Home","500,000đ","Chung cư","Hồ Chí Minh","500,000đ"));
        HomeAdapter homeAdapter = new HomeAdapter(arrayList_room,getActivity());
        recyclerView_room.setAdapter(homeAdapter);
>>>>>>> 953d66b0e1062c34560347aeecf3a99369be82d3
        return view;
    }
}