package com.example.bookbearer.modpass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ModPassModel extends AppCompatActivity implements ModPassMod{

    private ModPassPres mPres;

    public ModPassModel(ModPassPres rp){
        mPres = rp;
    }

    @Override
    public void reAutenticateAndChangePass(String uNPass, String uOPass) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), uOPass);
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                user.updatePassword(uNPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    mPres.sendMessage("Password modificata");
                                    mPres.finished();
                                }
                            }
                        });
            }
        });
    }

}
