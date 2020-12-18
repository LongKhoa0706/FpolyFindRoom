package com.longkhoa.fpolyfindroom.view.home.Customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.BannerAdapter;
import com.longkhoa.fpolyfindroom.adapter.CateroriesAdapter;
import com.longkhoa.fpolyfindroom.adapter.HomeRoomAdapter;
import com.longkhoa.fpolyfindroom.model.CategoriesRoom;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.model.User;
import com.longkhoa.fpolyfindroom.presenter.room.RoomInterface;
import com.longkhoa.fpolyfindroom.presenter.room.RoomPresenter;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;
import com.longkhoa.fpolyfindroom.view.room.DetailRoomFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeCustomerFragment extends Fragment implements RoomInterface, CallbackRoomAdapter  {
    ViewPager viewPagerBanner;
    RecyclerView recyclerView1, recyclerView2,recyclerView3;
    RoomPresenter roomPresenter;
    ProgressBar progressBar;
    BannerAdapter bannerAdapter;
    ArrayList<Integer> arrListBanner = new ArrayList<>();
    ArrayList<CategoriesRoom> arrayCateries = new ArrayList<>();
    CateroriesAdapter cateroriesAdapter;
    HomeRoomAdapter homeRoomAdapter;
    ArrayList<MyStatusRoom> arrHomeRoom = new ArrayList<>();
    ArrayList<Room> arrHomeItem = new ArrayList<>();
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;

    final long PERIOD_MS = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_customer_fragment, container, false);
        viewPagerBanner = view.findViewById(R.id.viewPagerBanner);
        recyclerView1 = view.findViewById(R.id.reyclerView1);
        recyclerView2 = view.findViewById(R.id.reyclerView2);
        recyclerView3 = view.findViewById(R.id.reyclerView3);
        progressBar = view.findViewById(R.id.progress_circular);
        roomPresenter = new RoomPresenter(this);
        roomPresenter.getListRoomm();
        arrListBanner.add(R.drawable.banner);
        arrListBanner.add(R.drawable.banner4);
        arrListBanner.add(R.drawable.banner3);
        bannerAdapter = new BannerAdapter(getActivity(), arrListBanner);
        viewPagerBanner.setAdapter(bannerAdapter);
        Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == arrListBanner.size() - 1) {
                    currentPage = 0;
                }
                viewPagerBanner.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        arrayCateries.add(new CategoriesRoom("Nhà", R.drawable.baseline_home_black_24dp));
        arrayCateries.add(new CategoriesRoom("Phòng", R.drawable.baseline_meeting_room_black_24dp));
        arrayCateries.add(new CategoriesRoom("Căn hộ", R.drawable.baseline_apartment_black_24dp));

        cateroriesAdapter = new CateroriesAdapter(getActivity(), arrayCateries, R.layout.custom_categories);
        recyclerView1.setAdapter(cateroriesAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        recyclerView2.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManagerHorizone = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView3.setLayoutManager(linearLayoutManagerHorizone);
        return view;
    }
    @Override
    public void getListRoom(MyStatusRoom myStatusRoom) {
        LinearLayoutManager linearLayoutManagerHorizone = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView3.setLayoutManager(linearLayoutManagerHorizone);
        homeRoomAdapter = new HomeRoomAdapter(getActivity(), myStatusRoom.getData(), R.layout.custom_item_homeroom,this);
        recyclerView3.setAdapter(homeRoomAdapter);
        progressBar.setVisibility(View.GONE);
        homeRoomAdapter.notifyDataSetChanged();
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
