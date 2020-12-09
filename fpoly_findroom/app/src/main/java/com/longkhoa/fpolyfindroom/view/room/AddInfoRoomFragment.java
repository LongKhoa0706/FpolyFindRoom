package com.longkhoa.fpolyfindroom.view.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.view.BottomSheet;

public class AddInfoRoomFragment extends Fragment {
    MaterialSpinner spinnerChooseType;
    Button btnCreateRoom;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addroominfo_fragment,container, false);
        spinnerChooseType = view.findViewById(R.id.spinner);
        btnCreateRoom = view.findViewById(R.id.btnCreateRoom);
        spinnerChooseType.setItems("Nhà", "Căn hộ", "Phòng");
        spinnerChooseType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        btnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getFragmentManager(),"bottomSheet");
            }
        });
        return view;
    }
}
