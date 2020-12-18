package com.longkhoa.fpolyfindroom.view.profile;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;


import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.longkhoa.fpolyfindroom.R;

import com.longkhoa.fpolyfindroom.model.MyStatusUser;
import com.longkhoa.fpolyfindroom.model.User;
import com.longkhoa.fpolyfindroom.presenter.user.UserInterface;
import com.longkhoa.fpolyfindroom.presenter.user.UserPresenter;
import com.longkhoa.fpolyfindroom.view.activity.ClientActivity;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment implements UserInterface {
    Button btn_own;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 2;
    private UserPresenter userPresenter;
    TextView tv_name, tv_phone, tv_email,tv_birthday, tv_signout, tv_update;
    ImageView img_alert1, img_alert2,img_camera,imageSmall;
    private static final int REQUEST_CODE_PERMISSIONS =1;
    private String currentImagePath;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        //ánh xạ
        tv_name = view.findViewById(R.id.txt_name);
        tv_phone = view.findViewById(R.id.txt_phone);
        tv_email = view.findViewById(R.id.txt_email);
        tv_birthday = view.findViewById(R.id.txt_date);
        tv_update = view.findViewById(R.id.txt_update);
        tv_signout = view.findViewById(R.id.txt_signout);
        img_alert1 = view.findViewById(R.id.img_alert);
        img_alert2 = view.findViewById(R.id.img_alert1);
        imageSmall=view.findViewById(R.id.capturedImageSmall);
        img_camera=view.findViewById(R.id.img_camera);
        btn_own = view.findViewById(R.id.btn_own);
        btn_own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ClientActivity.class);
                getActivity().startActivity(i);
            }
        });
        userPresenter = new UserPresenter(this);

        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.CAMERA
                )!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                        getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE
                )!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, REQUEST_CODE_PERMISSIONS);

                }else {
                    dispathCaptureImageIntent();

                }

            }
        });


        //update
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder altdial = new AlertDialog.Builder(getActivity());
                final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.update_profile,null);
                altdial.setView(dialogView);

                final AlertDialog dialog = altdial.create();
                dialog.show();

                //anh xa

                final TextView tv_name_dialog = dialogView.findViewById(R.id.txt_name);
                final TextView tv_email_dialog = dialogView.findViewById(R.id.txt_email);
                final TextView tv_phone_dialog = dialogView.findViewById(R.id.txt_phone);
                final TextView tv_birthday_dialog = dialogView.findViewById(R.id.txt_date);
                Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
                Button btn_xacnhan = dialogView.findViewById(R.id.btn_xacnhan);

                //get information
                tv_name_dialog.setText(tv_name.getText().toString());
                tv_email_dialog.setText(tv_email.getText().toString());
                tv_phone_dialog.setText(tv_phone.getText().toString());
                tv_birthday_dialog.setText(tv_birthday.getText().toString());

                //sự kiên
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = tv_name_dialog.getText().toString();
                        String email = tv_email_dialog.getText().toString();
                        String phone = tv_phone_dialog.getText().toString();
                        String birthday = tv_birthday_dialog.getText().toString();
//                        User user = new User();
//                       tv_email.setText(user.getEmail());
//                        userPresenter.update(user);
                        tv_name.setText(name);
                        tv_email.setText(email);
                        tv_phone.setText(phone);
                        tv_birthday.setText(birthday);
                        if (name.length()>0 && email.length()>0 && phone.length()>0&& birthday.length()>0){
                            Toast.makeText(getContext(), "update thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "update thất bại", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


            }
        });
        //đăng xuất
        tv_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(getActivity());
                altdial.setMessage("Bạn có muốn đăng xuất tài khoản này hay không?")
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(getActivity(), DashBoardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        });
                AlertDialog alert = altdial.create();
                alert.show();

            }
        });
        return view;
    }

    private void dispathCaptureImageIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager())!=null){
            File imageFile = null;
            try {
                imageFile = cretaImageFile();
            }catch (IOException e){
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if (imageFile!=null){
                Uri imageUri = FileProvider.getUriForFile(
                        getContext(),
                        "com.longkhoa.fpolyfindroom.view.profile.fileprovider",
                        imageFile
                );
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,REQUEST_CODE_CAPTURE_IMAGE);
            }
        }
    }
    private File cretaImageFile() throws IOException{
        String filename = "IMAGE_"
              +new SimpleDateFormat(
                      "yyyy_MM_dd_HH_mm_ss", Locale.getDefault()
        ).format(new Date());
        File directory =getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                filename,
                ".jpg",
                directory
        );
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==REQUEST_CODE_PERMISSIONS && grantResults.length>0){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED
            &&grantResults[1]==PackageManager.PERMISSION_GRANTED){
                dispathCaptureImageIntent();
            }else {
                Toast.makeText(getContext(), "not all permissions granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == RESULT_OK){
            try{
                //display small image
                imageSmall.setImageBitmap(getScaledBitmap(imageSmall));
            }catch (Exception e){
//                Log.d("hình đâu rồi","hinhdaau r");
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private Bitmap getScaledBitmap(ImageView imageView){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        int scaleFactor = Math.min(
                options.outWidth/imageView.getWidth(),
                options.outHeight/imageView.getHeight()
                );
        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        options.inPurgeable= true;
        return BitmapFactory.decodeFile(currentImagePath,options);


    }


    @Override
    public void getProfile(MyStatusUser myStatus) {

    }
}

