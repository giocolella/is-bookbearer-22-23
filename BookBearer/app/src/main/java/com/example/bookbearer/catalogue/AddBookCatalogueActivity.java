package com.example.bookbearer.catalogue;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.R;
import com.example.bookbearer.beans.Books;

//Visualizza la view per l'aggiunta di un libro al catalogo
public class AddBookCatalogueActivity extends AppCompatActivity   implements CatalogueView{
    private EditText ISBN;
    private EditText titolo;
    private EditText autore;
    private EditText genere;
    private EditText annoUscita;
    private EditText numPagine;
    private EditText linkImg;
    private Button catalogueOk;
    private Button catalogueAnnulla;

    private CataloguePres presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook_layout);

        ISBN = findViewById(R.id.catalogueISBN);
        titolo = findViewById(R.id.catalogueTitle);
        autore = findViewById(R.id.catalogueAuthor);
        genere = findViewById(R.id.catalogueGenre);
        annoUscita = findViewById(R.id.catalogueDate);
        numPagine = findViewById(R.id.cataloguePages);
        linkImg = findViewById(R.id.catalogueImg);
        catalogueOk = findViewById(R.id.catalogueOk);
        catalogueAnnulla = findViewById(R.id.catalogueAnnulla);

        catalogueOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.validateBook(ISBN.getText().toString(),titolo.getText().toString(),autore.getText().toString(),genere.getText().toString(),annoUscita.getText().toString(),linkImg.getText().toString(),numPagine.getText().toString())){
                    Books book = new Books(ISBN.getText().toString(),titolo.getText().toString(),autore.getText().toString(),genere.getText().toString(),annoUscita.getText().toString(),linkImg.getText().toString(),numPagine.getText().toString());
                    Log.d("zacatalogue", book.getAutore());
                    presenter.addCatBook(book);
                }
            }
        });

        catalogueAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        presenter = new CataloguePresenter(this);

    }

    @Override
    public void catalogueMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    //Termina l'activity
    //@Pre: L'activity non è in stato "distrutta"
    //@Post: L'activity è in stato "distrutta"
    @Override
    public void bookAdded() {
        finish();
    }

    public CataloguePres getPresenter(){
        return presenter;
    }

}
