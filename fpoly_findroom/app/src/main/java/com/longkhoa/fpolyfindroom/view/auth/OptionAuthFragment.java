package com.longkhoa.fpolyfindroom.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.view.auth.LoginFragment;
import com.longkhoa.fpolyfindroom.view.auth.RegisterFragment;

public class OptionAuthFragment extends Fragment {
    Button btnRegister;
    TextView txtLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.option_fragment, container, false);
        txtLogin = view.findViewById(R.id.txtLogin);
        btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment registerFragment = new RegisterFragment();
                loadFragment(registerFragment);
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();
                loadFragment(loginFragment);
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
