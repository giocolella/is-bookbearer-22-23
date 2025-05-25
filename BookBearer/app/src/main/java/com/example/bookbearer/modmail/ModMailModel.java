package com.example.bookbearer.modmail;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ModMailModel extends AppCompatActivity implements ModMailMod{

    private ModMailPres mPres;

    public ModMailModel(ModMailPres rp){
        mPres = rp;
    }

    @Override
    public void reAutenticateAndChangeMail(String uMail, String uPass) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), uPass);
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.updateEmail(uMail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            mPres.sendMessage("Email modificata");
                                            mPres.finished();
                                        }
                                        else {
                                            mPres.sendMessage("Email già esistente");
                                        }
                                    }
                                });
                    }
                });
    }
}
