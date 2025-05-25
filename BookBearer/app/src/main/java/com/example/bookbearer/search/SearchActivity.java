package com.example.bookbearer.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.R;
import com.example.bookbearer.beans.LightBook;
import com.example.bookbearer.bookinfo.BookInfoActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;


public class SearchActivity extends AppCompatActivity  implements SearchView{

    private ListView listview;
    private String testo = null;
    private Intent in = null;
    private SearchPres presenter;
    private LightBook lb = new LightBook();
    private ListAdapter la;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        in = getIntent();
        listview = findViewById(R.id.searchList);
        testo = in.getStringExtra("Title");
        presenter = new SearchPresenter(this);

        searching(testo);
    }

    private ArrayList<LightBook> searching(String testo){
        return presenter.getSearched(testo);
    }

    @Override
    public void searchMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void useResult(ArrayList<LightBook> arrayLb) {
        Log.d("zasult","gothere");

        la = new ListAdapter(SearchActivity.this,arrayLb);

        listview.setAdapter(la);

        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LightBook lBook = (LightBook) adapterView.getItemAtPosition(i);
                Intent in = new Intent(SearchActivity.this, BookInfoActivity.class);
                in.putExtra("ISBN",lBook.getIsbn());
                startActivity(in);
            }
        });
    }

    public SearchPres getPresenter(){
        return presenter;
    }

}
