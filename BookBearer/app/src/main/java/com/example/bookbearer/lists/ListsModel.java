package com.example.bookbearer.lists;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.beans.LightBook;
import com.example.bookbearer.beans.ListRead;
import com.example.bookbearer.beans.ListToBeRead;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
//Interagisce con le collezioni ListaLetti e ListaDaLeggere del database
public class ListsModel extends AppCompatActivity implements ListsMod{

    private ListsPres lPresenter;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private List<Map<String,Object>> mRead = new ArrayList<>();
    private List<Map<String,Object>> mToBeRead = new ArrayList<>();

    public ListsModel(){

    }

    public ListsModel(ListsPres lp){
        lPresenter = lp;
    }

    //Trova i libri della lista dei libri già letti di un utente
    //@Post: I libri vengono passati al presente
    @Override
    public void getRead() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Lists").document(auth.getCurrentUser().getUid()).collection("ListaLetti").document(auth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String,Object> listaLetti = document.getData();
                        for (Map.Entry<String, Object> entry : listaLetti.entrySet()){
                            String key = entry.getKey();
                            mRead.add((Map<String, Object>) listaLetti.get(key));
                        }
                        lPresenter.giveReadResults(mRead);
                        mRead = new ArrayList<>();
                    } else {
                        //gestire errore
                    }
                } else {
                    Log.d("zalists", "Errore lista letti");
                }
            }
        });
    }

    //Trova i libri della lista dei libri da leggere di un utente
    //@Post: i libri vengono passati al presenter
    @Override
    public void getToBeRead() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Lists").document(auth.getCurrentUser().getUid()).collection("ListaDaLeggere").document(auth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String,Object> listaDaLeggere = document.getData();
                        for (Map.Entry<String, Object> entry : listaDaLeggere.entrySet()){
                            String key = entry.getKey();
                            mToBeRead.add((Map<String, Object>) listaDaLeggere.get(key));
                        }
                        lPresenter.giveToBeReadResults(mToBeRead);
                        mToBeRead = new ArrayList<>();
                    } else {
                        //gestire errore
                    }
                } else {
                    Log.d("zalists", "Errore lista letti");
                }
            }
        });
    }

    @Override
    public void checkLists() {
        //da fare in bookinfo
    }

    @Override
    public void resultsReadArrived(List<Map<String,Object>> l) {
        lPresenter.giveReadResults(l);
    }

    @Override
    public void resultsToBeReadArrived(List<Map<String, Object>> l) {
        lPresenter.giveToBeReadResults(l);
    }

    //Cancella un libro dalla lista dei libri da leggere e lo aggiunge alla lista dei libri già letti
    //@Pre: m.* != null, il libro è presente nella lista dei libri da leggere
    //@Post: i libro è presente nella lista dei libri già letti e non in quella dei libri da leggere
    @Override
    public void addRead(Map<String, Object> m) {
        Map<String,Object> book = new HashMap<>();
        book.put(m.get("ISBN").toString(),m);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Lists").document(auth.getCurrentUser().getUid()).collection("ListaDaLeggere").document(auth.getCurrentUser().getUid());
        docRef.update(m.get("ISBN").toString(),FieldValue.delete()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                DocumentReference docRef = db.collection("Lists").document(auth.getCurrentUser().getUid()).collection("ListaLetti").document(auth.getCurrentUser().getUid());
                docRef.set(book, SetOptions.merge());
            }
        });
    }

    //Cancella un libro dalla lista dei libri già letti
    //@Pre: m.* != null, il libro è presente nella lista dei libri già letti
    //@Post: il libro non è presente nella lista dei libri già letti
    @Override
    public void deleteRead(Map<String,Object> m) {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Lists").document(auth.getCurrentUser().getUid()).collection("ListaLetti").document(auth.getCurrentUser().getUid());
        docRef.update(m.get("ISBN").toString(),FieldValue.delete()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("li","deleted");
            }
        });
    }

    //Cancella un libro dalla lista dei libri da leggere
    //@Pre: m.* != null, il libro è presente nella lista dei libri da leggere
    //@Post: il libro non è presente nella lista dei libri da leggere
    @Override
    public void deleteToBeRead(Map<String, Object> m) {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Lists").document(auth.getCurrentUser().getUid()).collection("ListaDaLeggere").document(auth.getCurrentUser().getUid());
        docRef.update(m.get("ISBN").toString(),FieldValue.delete()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("li","deleted");
            }
        });
    }


}
