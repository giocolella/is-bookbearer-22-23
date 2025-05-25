package com.example.bookbearer.catalogue;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.beans.Books;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CatalogueModel extends AppCompatActivity  implements CatalogueMod {

    private CataloguePres cPresenter;
    private FirebaseFirestore db;
    private Map<String,Object> m = new HashMap<>();

    public CatalogueModel(CataloguePres cp) {
        cPresenter = cp;
    }

    //Controlla se un libro già esiste nel database
    //@Pre: book.* != null
    //@Post: Se il libro già esiste chiede al presenter di mandare un Toast all'utente sennò chiama un altro metodo
    @Override
    public void addCatalogueBook(Books book) {
        db = FirebaseFirestore.getInstance();
        DocumentReference docIdRef = db.collection("Books").document(book.getIsbn());
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        cPresenter.sendMessage("Libro già esistente");
                    } else {
                        m.put("ISBN",book.getIsbn());
                        m.put("NumPagine",book.getNumPagine());
                        m.put("annoUscita",book.getData());
                        m.put("autore",book.getAutore());
                        m.put("bookImg",book.getBookImg());
                        m.put("genere",book.getGenere());
                        m.put("titolo",book.getTitolo());

                        addBook(m,book);
                    }
                } else {
                    //gestire errore
                }
            }
        });
    }

    //Inserisce il libro nel database
    //@Pre: m.* != null, book.* != null
    //@Post: Aggiunge il libro al database
    private void addBook(Map<String,Object> m,Books book){
        db.collection("Books").document(book.getIsbn()).set(m)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        cPresenter.sendMessage("Libro aggiunto");
                        cPresenter.closeActivity();
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
