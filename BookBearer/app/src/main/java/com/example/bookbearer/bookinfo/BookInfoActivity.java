package com.example.bookbearer.bookinfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookbearer.R;
import com.example.bookbearer.addreview.AddReviewActivity;
import com.example.bookbearer.reviews.ReviewActivity;

import java.util.Map;

//Visualizza le informazioni di un libro
public class BookInfoActivity extends AppCompatActivity implements BookInfoView{

    private ImageView iv;
    private TextView title;
    private TextView author;
    private TextView genere;
    private TextView annoUscita;
    private TextView numPagine;
    private TextView theBearers;
    private Button addToBeRead;
    private Button readReview;
    private Button addReview;
    private Button removeReview;

    private String ISBN;
    private BookInfoPres presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info);

        iv = findViewById(R.id.bookImg);
        title = findViewById(R.id.bookTitle);
        author = findViewById(R.id.bookAuthor);
        genere = findViewById(R.id.bookGenre);
        annoUscita = findViewById(R.id.bookDate);
        numPagine = findViewById(R.id.bookPages);
        theBearers = findViewById(R.id.theBearers);
        addToBeRead = findViewById(R.id.addToTheToBeReadList);
        readReview = findViewById(R.id.readReviews);
        addReview = findViewById(R.id.addReview);
        removeReview = findViewById(R.id.removeReview);

        Intent in = getIntent();
        ISBN = in.getStringExtra("ISBN");
        presenter = new BookInfoPresenter(this);

        addToBeRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addToTheToBeRead(ISBN);
            }
        });

        readReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rin = new Intent(BookInfoActivity.this, ReviewActivity.class);
                rin.putExtra("rISBN", ISBN);
                startActivity(rin);
            }
        });

        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkReview(ISBN);
            }
        });

        removeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteReview(ISBN);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.searchBookInfo(ISBN);
    }

    //Visualizza un Toast
    //@Pre: nessun Toast è visualizzato
    //@Post: Un Toast con la string msg viene visualizzato
    @Override
    public void bookInfoMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    //Visualizza le info del libro
    //@Pre: mp.* != null
    //@Post: le view visualizzano i corretti testi
    @Override
    public void useBookResult(Map<String, Object> mp) {
        title.setText("Titolo: " + String.valueOf(mp.get("titolo")));
        author.setText("Autore: " + String.valueOf(mp.get("autore")));
        genere.setText("genere: " + String.valueOf(mp.get("genere")));
        annoUscita.setText("Anno d'uscita: " + String.valueOf(mp.get("annoUscita")));
        numPagine.setText("Numero di pagine: " + String.valueOf(mp.get("NumPagine")));
        Glide.with(getApplicationContext()).load(mp.get("bookImg")).centerCrop().into(iv);
    }

    //Passa all'activity AddReviewActivity
    //@Post: Ci si trova in AddReviewActivity e BookInfoActivity è stata aggiunta nello stack
    @Override
    public void sendToAddReview() {
        Intent adin = new Intent(BookInfoActivity.this, AddReviewActivity.class);
        adin.putExtra("arISBN", ISBN);
        startActivity(adin);
    }

    public BookInfoPres getPresenter(){
        return presenter;
    }

}
