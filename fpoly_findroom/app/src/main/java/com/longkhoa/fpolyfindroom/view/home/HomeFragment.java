package com.longkhoa.fpolyfindroom.view.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
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
import com.longkhoa.fpolyfindroom.view.home.Customer.HomeCustomerFragment;
import com.longkhoa.fpolyfindroom.view.home.Inkeeper.HomeInkeeperFragment;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;
import com.longkhoa.fpolyfindroom.view.room.DetailRoomFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment  {
    User user = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(Constant.KEY_ACCOUNT, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("user","");
        user = gson.fromJson(json,User.class);
        if (user.getRoles().get(0).getRoleName().equals("innkeeper")){
            HomeInkeeperFragment homeInkeeperFragment = new HomeInkeeperFragment();
            loadFragment(homeInkeeperFragment);
        }else {
            HomeCustomerFragment homeCustomerFragment = new HomeCustomerFragment();
            loadFragment(homeCustomerFragment);
        }
        return null;
    }

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame, fragment).commit();
            return true;
        }
        return false;
    }

}
