package com.example.bookbearer.reviews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.R;
import com.example.bookbearer.beans.LightBook;
import com.example.bookbearer.beans.Review;
import com.example.bookbearer.bookinfo.BookInfoActivity;
import com.example.bookbearer.search.ListAdapter;
import com.example.bookbearer.search.SearchActivity;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements ReviewView{

    private String rISBN;
    private ReviewPres presenter;
    private ReviewListAdapter rLa;
    private ListView listview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews_layout);

        Intent in = getIntent();
        rISBN = in.getStringExtra("rISBN");
        listview = findViewById(R.id.listReviews);

        presenter = new ReviewPresenter(this);

        if(rISBN != null) {
            presenter.searchReviews(rISBN);
        }
    }

    @Override
    public void reviewMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void userResults(ArrayList<Review> results) {
        Log.d("zasult","gothere");

        rLa = new ReviewListAdapter(ReviewActivity.this,results);

        listview.setAdapter(rLa);

        listview.setClickable(false);
    }

    public ReviewPres getPresenter(){
        return presenter;
    }

}
