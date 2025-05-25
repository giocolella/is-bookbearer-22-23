package com.example.bookbearer.modprofilepic;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ModProfilePicModel extends AppCompatActivity implements ModProfilePicMod{

    private ModProfilePicPres mPresenter;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private Map<String,Object> mp = new HashMap<>();

    public ModProfilePicModel(ModProfilePicPres mp){
        mPresenter = mp;
    }

    @Override
    public void uploadImage(Uri imageUri) {
        if(imageUri != null){
            mPresenter.sendMessage("Upload iniziato");
            Log.d("zasult", imageUri.toString());
            db = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();
            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("profileImages").child(System.currentTimeMillis() + "." + mPresenter.getFileExstension(imageUri));
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String uriDownload = uri.toString();
                            Log.d("zasult",uriDownload);
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(Uri.parse(uriDownload))
                                    .build();
                            user.updateProfile(profileUpdates);
                            mp.put("profileImg",uriDownload);
                            db.collection("Users").document(user.getUid()).set(mp, SetOptions.merge());
                            mPresenter.sendMessage("Upload terminato");
                            mPresenter.giveImage(uriDownload);
                        }
                    });
                }
            });

        }
    }
}
