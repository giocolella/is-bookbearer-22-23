package com.example.bookbearer.bookinfo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.beans.ListToBeRead;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Interagisce con il database riguardo alla collezione Books e alla collezione Lists
public class BookInfoModel extends AppCompatActivity implements BookInfoMod {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private Map<String, Object> mp = new HashMap<String,Object>();
    private BookInfoPres bPresen;

    public BookInfoModel(BookInfoPres rp){
        bPresen = rp;
    }

    //Seleziona un libro da visualizzare
    //@Pre: ISBN.lenght() = 14
    //@Post: il libro viene trovato e le info vengono aggiunte in una Map
    @Override
    public void getSelectedBook(String ISBN) {
        db = FirebaseFirestore.getInstance();
        Log.d("zasult", ISBN);
        DocumentReference docRef = db.collection("Books").document(ISBN.trim());
        docRef.get().addOnCompleteListener(BookInfoModel.this,new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("zasult", "Document exists");
                        mp = document.getData();
                        bPresen.bookArrived(mp);
                    } else {
                        Log.d("zasult", "No such document");
                    }
                } else {
                    Log.d("zasult", "failed");
                }
            }
        });
    }

    //Controlla se un libro è nella lista dei libri da leggere di un determinato utente
    //@Pre: ISBN.lenght() = 14
    //@Post:Se il libro esiste nella lista dei libri da leggere il metodo termina e se non esiste chiama un altro metodo
    @Override
    public void addToList(String ISBN) {
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
                            if(key.equals(ISBN)){
                                bPresen.sendMessage("Libro già in lista da leggere");
                                return;
                            }
                        }
                        checkRead(ISBN);
                    } else {
                        //gestire errore
                    }
                } else {
                    Log.d("zalists", "Errore lista letti");
                }
            }
        });
    }

    //Controlla se un libro è nella lista dei libri già letti di un determinato utente
    //@Pre: ISBN.lenght() = 14, il libro non è nella lista dei libri da leggere
    //@Post: Se il libro esiste nella lista dei libri già letti il metodo termina e se non esiste chiama un altro metodo.
    private void checkRead(String ISBN){
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
                            if(key.equals(ISBN)){
                                bPresen.sendMessage("Libro già in lista letti");
                                return;
                            }
                        }
                        addBookToList(ISBN);
                    } else {
                        //gestire errore
                    }
                } else {
                    Log.d("zalists", "Errore lista letti");
                }
            }
        });
    }

    //Aggiunge un libro alla lista dei libri da leggere di un determinato utente
    //@Pre: ISBN.lenght() = 14,il libro non è in nessuna delle due liste
    //@Post: Il libro è presente nella lista dei libri da leggere di un determinato utente
    private void addBookToList(String ISBN){
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        DocumentReference docRef = db.collection("Books").document(ISBN.trim());
        docRef.get().addOnCompleteListener(BookInfoModel.this,new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("zasult", "Document exists");
                        mp = document.getData();
                        Map<String,Object> m = new HashMap<>();
                        m.put("ISBN",mp.get("ISBN").toString());
                        m.put("titolo",mp.get("titolo").toString());
                        m.put("autore",mp.get("autore").toString());
                        m.put("bookImg",mp.get("bookImg").toString());
                        Map<String,Object> book = new HashMap<>();
                        book.put(mp.get("ISBN").toString(),m);
                        DocumentReference docRef = db.collection("Lists").document(auth.getCurrentUser().getUid()).collection("ListaDaLeggere").document(auth.getCurrentUser().getUid());
                        docRef.set(book, SetOptions.merge());
                        bPresen.sendMessage("Libro aggiunto alla lista dei libri da leggere");
                    } else {
                        Log.d("zasult", "No such document");
                    }
                } else {
                    Log.d("zasult", "failed");
                }
            }
        });
    }


    //Controlla se una recensione che un utente vuole aggiungere ad un libro e la seconda da parte sua
    //@Pre: ISBN.lenght() = 14
    //@Post: se è la seconda chiede al Presenter di mandare un Toast all'utente, sennò chiama un altro metodo
    @Override
    public boolean checkIfSecond(String ISBN) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        DocumentReference docIdRef = db.collection("Books").document(ISBN).collection("Reviews").document(user.getUid());
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        bPresen.sendMessage("Recensione già aggiunta");
                    }else{
                        bPresen.sendToAR();
                    }
                } else {
                    //gestire errore
                }
            }
        });
        return false;
    }

    //Elimina la recensione di un utente ad un determinato libro
    //@Pre: ISBN.lenght() = 14
    @Override
    public void deleteReviewDocument(String ISBN) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        db.collection("Books").document(ISBN).collection("Reviews").document(user.getUid()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        bPresen.sendMessage("Recensione cancellata");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("zureview", "Error deleting document", e);
                    }
                });
    }

}
