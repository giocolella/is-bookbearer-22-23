package com.example.bookbearer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookbearer.modmail.ModMailActivity;
import com.example.bookbearer.modpass.ModPassActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsFragment extends Fragment{

    private Button logout;
    private Button modMail;
    private Button modPass;
    private Button deleteAccount;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private FirebaseAuth auth;


    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        logout = view.findViewById(R.id.exit);
        modMail = view.findViewById(R.id.modMail);
        modPass = view.findViewById(R.id.modPass);
        deleteAccount = view.findViewById(R.id.deleteAccount);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signingOut();
            }
        });

        modMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ModMailActivity.class));
            }
        });

        modPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ModPassActivity.class));
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });

        return view;
    }

    private void signingOut(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getContext(),"Logged out!", Toast.LENGTH_SHORT);
        startActivity(new Intent(getContext(),StartActivity.class));
        getActivity().finish();
    }

    private void deleteAccount(){
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        String userId = auth.getCurrentUser().getUid();
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    db.collection("Lists").document(userId).collection("ListaLetti").document(userId).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            db.collection("Lists").document(userId).collection("ListaDaLeggere").document(userId).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    db.collection("Lists").document(userId).delete();
                                }
                            });
                        }
                    });
                    db.collection("Users").document(userId).delete();
                    Toast.makeText(getContext(),"Account eliminato!", Toast.LENGTH_SHORT);
                    startActivity(new Intent(getContext(),StartActivity.class));
                    getActivity().finish();
                }
            }
        });
    }

}