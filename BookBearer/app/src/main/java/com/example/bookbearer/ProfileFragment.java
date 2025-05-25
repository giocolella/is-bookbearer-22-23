package com.example.bookbearer;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookbearer.modprofilepic.ModProfilePicPres;
import com.example.bookbearer.modprofilepic.ModProfilePicPresenter;
import com.example.bookbearer.modprofilepic.ModProfilePicView;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment implements ModProfilePicView {

    private ImageView iv;
    private TextView profileUsername;

    private ModProfilePicPres presenter;
    private static final int IMAGE_REQUEST = 2;
    private FirebaseAuth auth;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        iv = view.findViewById(R.id.profilePic);
        profileUsername = view.findViewById(R.id.profileUsername);
        presenter = new ModProfilePicPresenter(this);
        if(auth.getCurrentUser().getPhotoUrl() != null){
            Glide.with(getContext()).load(auth.getCurrentUser().getPhotoUrl().toString()).centerCrop().into(iv);
        }
        if(auth.getCurrentUser().getDisplayName() != null){
            profileUsername.setText(auth.getCurrentUser().getDisplayName());
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setType("image/*");
                in.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(in,IMAGE_REQUEST);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == IMAGE_REQUEST){
            Uri imgUri = data.getData();
            presenter.uploadImage(imgUri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG);
    }

    @Override
    public String getExtension(Uri uri) {
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void setProfileImg(String uri) {
        Glide.with(getContext()).load(uri).centerCrop().into(iv);
    }


}