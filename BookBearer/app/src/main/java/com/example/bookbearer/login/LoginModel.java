package com.example.bookbearer.login;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginModel extends AppCompatActivity implements LogModel{

    private LogPresenter lPresenter;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private boolean val;
    private Map<String, Object> listaLetti = new HashMap<>();
    private Map<String, Object> listaDaleggere = new HashMap<>();
    private Map<String,Object> user = new HashMap<>();
    private Map<String, Object> cataloghist = new HashMap<>();

    public LoginModel(LogPresenter lp){
        lPresenter = lp;
    }

    @Override
    public void loginUser(String uMail, String uPass) {
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(uMail,uPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                lPresenter.sendMessage("Login effettuato!");
                String userId = auth.getCurrentUser().getUid();
                checkList(userId);
                lPresenter.signUser();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                lPresenter.sendMessage("Credenziali errate!");
            }
        });
    }

    @Override
    public void checkList(String userId) {
        db = FirebaseFirestore.getInstance();
        DocumentReference docIdRef = db.collection("Lists").document(userId);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                    }else{
                        db.collection("Lists").document(userId).set(listaLetti).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                db.collection("Lists").document(userId).collection("ListaLetti").document(userId).set(listaLetti);
                            }
                        });
                        db.collection("Lists").document(userId).set(listaDaleggere).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                db.collection("Lists").document(userId).collection("ListaDaLeggere").document(userId).set(listaDaleggere);
                            }
                        });
                        user.put("email",auth.getCurrentUser().getEmail());
                        user.put("nomeUtente",auth.getCurrentUser().getDisplayName());
                        db.collection("Users").document(userId).set(user);
                    }
                } else {
                    //gestire errore
                }
            }
        });
    }

    @Override
    public void isCataloghist(String uMail,String uPass) {
        db = FirebaseFirestore.getInstance();
        DocumentReference docIdRef = db.collection("Cataloghist").document(uMail);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        cataloghist = document.getData();
                        if(cataloghist.get("pass").toString().equals(uPass)){
                            lPresenter.startCatalogue(uMail);
                        }else{
                            lPresenter.sendMessage("Password cataloghista errata");
                        }
                    }else{
                        loginUser(uMail,uPass);
                    }
                } else {
                    //gestire errore
                }
            }
        });
    }

    public void setValidate(boolean b){
        val = b;
    }

}
