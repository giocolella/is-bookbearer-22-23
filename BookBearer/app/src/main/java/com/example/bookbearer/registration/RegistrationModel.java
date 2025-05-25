package com.example.bookbearer.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class RegistrationModel extends AppCompatActivity implements RegModel{

    private RegPresenter regPresen;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public RegistrationModel(RegPresenter rp){
        regPresen = rp;
    }

    @Override
    public void registerUser(String uName,String uMail, String uPass) {
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(uMail,uPass).addOnCompleteListener(RegistrationModel.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(uName).build();
                    user.updateProfile(profileUpdates);
                    regPresen.sendToLogin();
                }else{
                    try
                    {
                        throw task.getException();
                    }
                    catch (FirebaseAuthUserCollisionException existEmail)
                    {
                        regPresen.sendMessage("Email già esistente!");
                    }
                    catch (Exception e)
                    {
                        //gestire errore
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
