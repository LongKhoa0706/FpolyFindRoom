package com.longkhoa.fpolyfindroom.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.longkhoa.fpolyfindroom.R;

import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class SendOTP extends AppCompatActivity {
    EditText inputMobile;
    Button buttonGetOTP;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);
        inputMobile = findViewById(R.id.inputMobile);
        buttonGetOTP = findViewById(R.id.buttonGetOTP);
        progressBar = findViewById(R.id.progressBar);
        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputMobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(SendOTP.this, "Enter mobile", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                buttonGetOTP.setVisibility(View.INVISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+84"+inputMobile.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        SendOTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                                Log.d("lôi",e.toString());
//                                Toast.makeText(SendOTP.this, "lỗi"+e.toString().isEmpty(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                                Intent i = new Intent(getApplicationContext(),VerifyActivity.class);
                                i.putExtra("mobile",inputMobile.getText().toString());
                                i.putExtra("verificationId",verificationId);
                                startActivity(i);
                            }
                        });

            }
        });
    }
}