package com.longkhoa.fpolyfindroom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment  extends Fragment {
    Button btnSubmitRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        btnSubmitRegister = view.findViewById(R.id.btnSubmitRegister);
        btnSubmitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyPhoneFragment verifyPhoneFragment = new VerifyPhoneFragment();
                loadFragment(verifyPhoneFragment);
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.frameClient, fragment).addToBackStack(null).commit();

        }
    }
}
