package com.example.bookbearer.search;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.beans.LightBook;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchModel extends AppCompatActivity implements SearchMod{

    private SearchPres sPresenter;
    private FirebaseFirestore db;
    private LightBook lb = new LightBook();
    private ArrayList<LightBook> arrayLb = new ArrayList<LightBook>();
    private ArrayList<Map<String,Object>> lightBook = new ArrayList<Map<String,Object>>();
    public SearchModel(SearchPres lp){
        sPresenter = lp;
    }

    @Override
    public ArrayList<LightBook> giveSearched(String title) {
        if(title == null){
            return arrayLb;
        }
        String titolo = title.toLowerCase();
        db = FirebaseFirestore.getInstance();
        db.collection("Books").whereEqualTo("titolo",titolo.trim()).get()
                .addOnCompleteListener(SearchModel.this,new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                lightBook.add(document.getData());
                                Log.d("zasult","map");
                            }
                            for(Map<String,Object> m : lightBook){
                                Log.d("zasult","here too");
                                lb.setTitle(String.valueOf(m.get("titolo")));
                                lb.setAuthor(m.get("autore").toString());
                                //Log.d("zasult",lb.getAuthor());
                                lb.setIsbn(m.get("ISBN").toString());
                                lb.setImageUrl(m.get("bookImg").toString());
                                arrayLb.add(lb);
                                lb = new LightBook();
                            }
                            //Log.d("zasult","sending result with" + arrayLb.get(0).getAuthor() + arrayLb.get(1).getAuthor() +arrayLb.size());
                            sPresenter.resultArrived(arrayLb);
                        } else {
                            Log.d("zasult","No result");
                            //gestire errore
                        }
                    }
                });
        return arrayLb;
    }

    public ArrayList<LightBook> getArrayLb(){
        return arrayLb;
    }

}
