package com.longkhoa.fpolyfindroom.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.longkhoa.fpolyfindroom.R;
import com.longkhoa.fpolyfindroom.adapter.ChooseImageAdapter;
import com.longkhoa.fpolyfindroom.view.activity.ClientActivity;
import com.longkhoa.fpolyfindroom.view.activity.DashBoardActivity;
import com.longkhoa.fpolyfindroom.view.room.AddInfoRoomFragment;
import com.longkhoa.fpolyfindroom.view.room.CreateRoomFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class ChooseImageFragment extends Fragment {
    MaterialToolbar toolBarChooseImage;
   final int REQUEST_CODE_CAMERA = 123;
   final int REQUEST_CODE_PickImage = 987;
   RecyclerView recyclerViewImage;
   AppCompatButton btnSaveImage;
   ChooseImageAdapter chooseImageAdapter;
   StorageReference storageReference;
   DatabaseReference databaseReference;
   File file;

    List<Uri> listImageUri = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_choose_image, container, false);
        toolBarChooseImage = view.findViewById(R.id.toolbarChooseImage);
        ((DashBoardActivity)getActivity()).setSupportActionBar(toolBarChooseImage);
//
        DashBoardActivity.bottomNavigationMenuView.setVisibility(View.GONE);
        recyclerViewImage = view.findViewById(R.id.reyclerViewChooseImage);
        btnSaveImage = view.findViewById(R.id.btnSaveImage);
        String radomKey = UUID.randomUUID().toString();
//        databaseReference = FirebaseDatabase.getInstance().getReference("imageRoom");

        btnSaveImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final ProgressDialog dialog = new ProgressDialog(getActivity());
                dialog.setTitle("Đang tải hình.....");
                dialog.show();
                for (Uri urifor : listImageUri){
                    String image = getFileName(urifor);
                    storageReference = FirebaseStorage.getInstance().getReference("image").child(image);
                    UploadTask uploadTask = storageReference.putFile(urifor);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    }).start();
                                }
                            });
                        }
                    });

                }
            }
        });
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_util,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG,"Permission not available requesting permission");
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);

        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA);
        }
    }

    private void checkForderPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG,"Permission not available requesting permission");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PickImage);

        } else {
            Intent photoPickerIntent = new Intent();
            photoPickerIntent.setType("image/*");
            photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(photoPickerIntent,"Select Image(s)"),REQUEST_CODE_PickImage);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Log.d("DDD","permission denied! Disable the function related with permission.");
                }
            }
            break;
            case REQUEST_CODE_PickImage: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Log.d("DDD","permission denied! Disable the function related with permission.");
                }
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PickImage && resultCode == RESULT_OK && data != null){
            if (data.getClipData() != null){
                int count = data.getClipData().getItemCount();
                for (int i = 0 ; i<count;i++){
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    listImageUri.add(imageUri);
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                recyclerViewImage.setLayoutManager(layoutManager);
                chooseImageAdapter = new ChooseImageAdapter(listImageUri,getActivity(),R.layout.custom_choose_image);
                recyclerViewImage.setAdapter(chooseImageAdapter);
                chooseImageAdapter.notifyDataSetChanged();

            }else {
                Uri uri = data.getData();
                listImageUri.add(uri);
                for (Uri uri1 : listImageUri){
//                    imgChooseRoom.setImageURI(uri1);
                }
            }

        };
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }}