package com.longkhoa.fpolyfindroom.view.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.BannerAdapter;
import com.longkhoa.fpolyfindroom.adapter.CateroriesAdapter;
import com.longkhoa.fpolyfindroom.adapter.HomeRoomAdapter;
import com.longkhoa.fpolyfindroom.model.Categories;
import com.longkhoa.fpolyfindroom.model.Room;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    ViewPager viewPagerBanner;
    RecyclerView recyclerView1, recyclerView2,recyclerView3;

    BannerAdapter bannerAdapter;
    ArrayList<Integer> arrListBanner = new ArrayList<>();
    ArrayList<Categories> arrayCateries = new ArrayList<>();
    CateroriesAdapter cateroriesAdapter;
    HomeRoomAdapter homeRoomAdapter;
    ArrayList<Room> arrHomeRoom = new ArrayList<>();
    ArrayList<Room> arrHomeItem = new ArrayList<>();
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        viewPagerBanner = view.findViewById(R.id.viewPagerBanner);
        recyclerView1 = view.findViewById(R.id.reyclerView1);
        recyclerView2 = view.findViewById(R.id.reyclerView2);
        recyclerView3 = view.findViewById(R.id.reyclerView3);

        arrListBanner.add(R.drawable.banner1);
        arrListBanner.add(R.drawable.banner2);
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
        arrayCateries.add(new Categories("Nhà", R.drawable.baseline_home_black_24dp));
        arrayCateries.add(new Categories("Phòng", R.drawable.baseline_meeting_room_black_24dp));
        arrayCateries.add(new Categories("Căn hộ", R.drawable.baseline_apartment_black_24dp));

        cateroriesAdapter = new CateroriesAdapter(getActivity(), arrayCateries, R.layout.custom_categories);
        recyclerView1.setAdapter(cateroriesAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        recyclerView2.setLayoutManager(gridLayoutManager);

        arrHomeRoom.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeRoom.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeRoom.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeRoom.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeRoom.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeRoom.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        homeRoomAdapter = new HomeRoomAdapter(getActivity(), arrHomeRoom, R.layout.custom_item_homeroom);
        recyclerView2.setAdapter(homeRoomAdapter);
//        recyclerView2.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManagerHorizone = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView3.setLayoutManager(linearLayoutManagerHorizone);

        arrHomeItem.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeItem.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeItem.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeItem.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeItem.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        arrHomeItem.add(new Room("1,000,000đ", "Nhà", "43/12 Trần Quang Diệu,Phường 14, Quận 3,TPHCM", "", null, "", false, null, "Nhà giá rẻ", ""));
        homeRoomAdapter = new HomeRoomAdapter(getActivity(), arrHomeItem, R.layout.custom_item_homeroom);
        recyclerView3.setAdapter(homeRoomAdapter);

        return view;
    }

}
 class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private int headerNum;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, int headerNum) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.headerNum = headerNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view) - headerNum; // item position

        if (position >= 0) {
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        }
    }
}
