package com.example.bookbearer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.R;
import com.example.bookbearer.catalogue.AddBookCatalogueActivity;

public class CatalogueActivity extends AppCompatActivity{

    private Button addBook;
    private Button exitCatalogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogue_layout);

        addBook = findViewById(R.id.addBook);
        exitCatalogue = findViewById(R.id.exitCatalogue);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CatalogueActivity.this, AddBookCatalogueActivity.class));
            }
        });

        exitCatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
