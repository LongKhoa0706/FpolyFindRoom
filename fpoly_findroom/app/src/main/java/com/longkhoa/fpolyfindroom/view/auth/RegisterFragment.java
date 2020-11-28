package com.longkhoa.fpolyfindroom.view.auth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.model.User;
import com.longkhoa.fpolyfindroom.presenter.RegisterInterface;
import com.longkhoa.fpolyfindroom.presenter.RegisterPresenter;

import es.dmoral.toasty.Toasty;

public class RegisterFragment  extends Fragment implements RegisterInterface {
    Button btnSubmitRegister;
    EditText edtUserName,edtPassword,edtEmail,edtPhone,edtRepassword;
//    ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private RegisterPresenter registerPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        btnSubmitRegister = view.findViewById(R.id.btnSubmitRegister);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        edtUserName = view.findViewById(R.id.edtUserName);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtRepassword = view.findViewById(R.id.edtRepassword);
        registerPresenter = new RegisterPresenter(this);

        btnSubmitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                VerifyPhoneFragment verifyPhoneFragment = new VerifyPhoneFragment();
//                loadFragment(verifyPhoneFragment);
               String email = edtEmail.getText().toString();
               String password = edtPassword.getText().toString();
               String phone = edtPhone.getText().toString();
               String userName = edtUserName.getText().toString();
               String repassword = edtRepassword.getText().toString();

                User user = new User("3","4","3333","12","","",false,"");
                registerPresenter.register(user);

            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.frameClient, fragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void registerSuccess() {
//        progressDialog.show();
        Toasty.success(getActivity(), "Success!", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void registerFail(String message) {
        progressDialog.dismiss();
        Toasty.error(getActivity(), message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
        Log.d("AAAA","SHOW");
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
        Log.d("AAAA","HIDE");
    }


}
