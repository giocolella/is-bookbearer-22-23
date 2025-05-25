package com.example.bookbearer.reviews;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.beans.LightBook;
import com.example.bookbearer.beans.Review;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ReviewModel extends AppCompatActivity implements ReviewMod{

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private Review review = new Review();
    private ArrayList<Review> reviewsArray = new ArrayList<Review>();
    private ArrayList<Map<String,Object>> reviewsMod = new ArrayList<Map<String,Object>>();

    private ReviewPres reviewPresenter;

    public ReviewModel(ReviewPresenter rp){
        reviewPresenter = rp;
    }

    @Override
    public ArrayList<Map<String,Object>> readReviews(String rISBN) {
        db = FirebaseFirestore.getInstance();
        db.collection("Books").document(rISBN).collection("Reviews").get()
                .addOnCompleteListener(ReviewModel.this,new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                reviewsMod.add(document.getData());
                                Log.d("zareview","map");
                            }
                            for(Map<String,Object> m : reviewsMod){
                                Log.d("zareview","here too");
                                review.setPunteggio(m.get("punteggio").toString());
                                review.setAutore(m.get("autore").toString());
                                review.setDescrizione(m.get("descrizione").toString());
                                reviewsArray.add(review);
                                review = new Review();
                            }
                            //Log.d("zasult","sending result with" + arrayLb.get(0).getAuthor() + arrayLb.get(1).getAuthor() +arrayLb.size());
                            reviewPresenter.resultsArrived(reviewsArray);
                        } else {
                            Log.d("zareview","No result");
                            //gestire errore
                        }
                    }
                });
        return reviewsMod;
    }
}
