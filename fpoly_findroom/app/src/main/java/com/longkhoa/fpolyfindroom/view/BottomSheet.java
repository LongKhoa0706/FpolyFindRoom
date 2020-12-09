package com.longkhoa.fpolyfindroom.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.view.room.CreateRoomFragment;

public class BottomSheet extends BottomSheetDialogFragment {
//    BottomSheetListner bottomSheetListner;
    Button btnYes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.bottom_sheet, container, false);
        btnYes = v.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateRoomFragment createRoomFragment = new CreateRoomFragment();
                loadFragment(createRoomFragment);
                dismiss();
            }
        });
        return v;
    }


    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.dashboard_frame, fragment).addToBackStack(null).commit();
        }
    }


}