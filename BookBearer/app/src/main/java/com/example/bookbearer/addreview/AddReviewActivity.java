package com.example.bookbearer.addreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.R;
import com.example.bookbearer.beans.Review;
import com.google.firebase.auth.FirebaseAuth;

//La view che mostra il layout per l'aggiunta della recensione
//Invariante: context AddReviewActivity inv: self.score != “”
public class AddReviewActivity extends AppCompatActivity implements AddReviewView{

    private String score = "NP";
    private String author;
    private String desc;
    private String arISBN;
    private EditText description;
    private Button ok;
    private Button annulla;
    private Review review;

    private AddReviewPres presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addreview_layout);


        author = "bearer";


        Intent in = getIntent();
        arISBN = in.getStringExtra("arISBN");
        presenter = new AddReviewPresenter(this);

        description = findViewById(R.id.addDescription);
        ok = findViewById(R.id.okReview);
        annulla = findViewById(R.id.annullaReview);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desc = description.getText().toString();
                if(presenter.validateReview(score,desc)) {
                    review = new Review(score, author, desc);
                    presenter.addReview(arISBN, review);
                }
            }
        });

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void onRadioButtonClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.add1:
                if (checked)
                   score = "1";
                    break;
            case R.id.add2:
                if (checked)
                    score = "2";
                    break;
            case R.id.add3:
                if (checked)
                    score = "3";
                    break;
            case R.id.add4:
                if (checked)
                    score = "4";
                    break;
            case R.id.add5:
                if (checked)
                    score = "5";
                    break;
            default: score = "NP";
        }
    }

    //Visualizza un Toast
    //@Pre: nessun Toast è visualizzato
    //@Post: Un Toast con la string msg viene visualizzato
    @Override
    public void addReviewMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    //Visualizza un Toast e termina l'activity
    //@Pre: nessun Toast visualizzato
    //@Post: Un Toast con la stringa "Recensione aggiunta" viene visualizzato e l'activity è in stato distrutta
    @Override
    public void reviewAdded() {
        addReviewMessage("Recensione aggiunta");
        finish();
    }

    public AddReviewPres getPresenter(){
        return presenter;
    }

}
