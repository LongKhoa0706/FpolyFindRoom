package com.longkhoa.fpolyfindroom.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.developer.kalert.KAlertDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.view.room.CreateRoomFragment;

public class BottomSheet extends BottomSheetDialogFragment {
    TextView txtEditRoom,txtRemoveRoom;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet, container, false);
        txtEditRoom = v.findViewById(R.id.txtEditRoom);
        txtRemoveRoom = v.findViewById(R.id.txtRemoveRoom);
        txtRemoveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("B1", "VO");
            }
        });

        txtEditRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return  v;
    }


    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.dashboard_frame, fragment).addToBackStack(null).commit();
        }
    }


}