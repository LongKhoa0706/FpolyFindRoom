package com.longkhoa.fpolyfindroom.view.home.Inkeeper;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.developer.kalert.KAlertDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.HomeInAdapter;
import com.longkhoa.fpolyfindroom.adapter.HomeRoomAdapter;

import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.presenter.room.deleteroom.DeleteRoomPresenter;
import com.longkhoa.fpolyfindroom.presenter.room.listmyroom.ListMyRoomInterface;
import com.longkhoa.fpolyfindroom.presenter.room.listmyroom.ListMyRoomPresenter;
import com.longkhoa.fpolyfindroom.util.Constant;
import com.longkhoa.fpolyfindroom.view.BottomSheet;
import com.longkhoa.fpolyfindroom.view.CallBackItemOption;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;
import com.longkhoa.fpolyfindroom.view.room.AddInfoRoomFragment;
import com.longkhoa.fpolyfindroom.view.room.CallbackRoomAdapter;
import com.longkhoa.fpolyfindroom.view.room.CreateRoomFragment;
import com.longkhoa.fpolyfindroom.view.room.DetailRoomFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HomeInkeeperFragment extends Fragment implements ListMyRoomInterface, CallbackRoomAdapter, CallBackItemOption {
    HomeInAdapter homeInAdapter;
    ListMyRoomPresenter listMyRoomPresenter;
    RecyclerView recyclerViewInkeeper;
    Button btnRoom;
    ProgressBar progressBar;
    DeleteRoomPresenter deleteRoomPresenter;
    BottomSheetDialog bottomSheet;
    ArrayList<Room> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_inkeeper_fragment, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.KEY_ACCOUNT, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        recyclerViewInkeeper = view.findViewById(R.id.reyclerViewManageRoom);
        btnRoom = view.findViewById(R.id.btnRoom);
        progressBar = view.findViewById(R.id.progress_circular);
        deleteRoomPresenter = new DeleteRoomPresenter();
        progressBar.setIndeterminateDrawable(new ChromeFloatingCirclesDrawable.Builder(getActivity())
                .build());
        listMyRoomPresenter = new ListMyRoomPresenter(this);
        listMyRoomPresenter.getListMyRoom("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI1ZmRlOGY2OTkxNzY4OTA2ZjRlMjNkNGYiLCJwaG9uZSI6IjA5MDMwOTIxIiwiaWF0IjoxNjA4NDI0NTk2LCJleHAiOjE2MTEwMTY1OTZ9.qIIJELW4QnbwmktkUOAiVbGBKL7dXrq8VsAtZwBqNIs");
        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame, new AddInfoRoomFragment()).commit();
            }
        });

        return view;

    }


    @Override
    public void getListMyRooms(MyStatusRoom myStatusRoom) {
        arrayList = myStatusRoom.getData();
        progressBar.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManagerHorizone = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewInkeeper.setLayoutManager(linearLayoutManagerHorizone);
        homeInAdapter = new HomeInAdapter(getActivity(), myStatusRoom.getData(), R.layout.custom_item_room, this, this);
        recyclerViewInkeeper.setAdapter(homeInAdapter);
        homeInAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickListenerCardView(Room room) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_ROOM, room);
        DetailRoomFragment detailRoomFragment = new DetailRoomFragment();
        detailRoomFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame, detailRoomFragment).addToBackStack(null).commit();
    }

    @Override
    public void onClickItem(Room room) {

        bottomSheet = new BottomSheetDialog(getActivity(), R.style.BottomSheetTheme);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet, getActivity().findViewById(R.id.bottomSheet));
        view.findViewById(R.id.txtRemoveRoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new KAlertDialog(getActivity(), KAlertDialog.WARNING_TYPE)
                        .setTitleText("Xoá bài đăng?")
                        .setContentText("Bạn có chắc muốn xoá phòng " + room.getTitle() + " này không? ")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                deleteRoomPresenter.deleteRoom(DashBoardActivity.Token,room.getId());
                                arrayList.remove(room);
                                homeInAdapter.notifyDataSetChanged();
                                kAlertDialog.dismissWithAnimation();
                            }
                        })
                        .show();
                bottomSheet.dismiss();
            }
        });
        view.findViewById(R.id.txtEditRoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = new Bundle();
                bundle1.putParcelable("room1", room);
                bundle1.putInt("option",1);
                CreateRoomFragment createRoomFragment = new CreateRoomFragment();
                createRoomFragment.setArguments(bundle1);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame, createRoomFragment).addToBackStack(null).commit();
                bottomSheet.dismiss();

            }
        });
        bottomSheet.setContentView(view);
        bottomSheet.show();
    }

    @Override
    public void onResume() {

        Log.d("ONRESUME","ONRESUME");
        super.onResume();
    }

}

