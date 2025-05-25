package com.example.bookbearer.addreview;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.beans.Review;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//Interagisce con il database riguardo alle collezioni Reviews
public class AddReviewModel extends AppCompatActivity implements AddReviewMod{

    private AddReviewPres arPresenter;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private Map<String,Object> m = new HashMap<>();

    public AddReviewModel(AddReviewPres arp){
        arPresenter = arp;
    }

    //Metodo che aggiunge una recensione al database
    //@Pre: è la prima recensione dell'utente, ISBN.lenght() = 14, review.* != null
    //@Post: la recensione è stata aggiunta al database
    @Override
    public void addReview(String ISBN,Review review) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        if(auth.getCurrentUser().getDisplayName() != null){
            review.setAutore(auth.getCurrentUser().getDisplayName());
        }

        m.put("punteggio", review.getPunteggio());
        m.put("autore",review.getAutore());
        m.put("descrizione", review.getDescrizione());

        db.collection("Books").document(ISBN).collection("Reviews").document(user.getUid()).set(m)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        arPresenter.reviewAdded();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       //gestire errore
                    }
                });

    }
}
