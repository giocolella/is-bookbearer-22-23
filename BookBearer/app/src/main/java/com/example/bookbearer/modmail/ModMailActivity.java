package com.example.bookbearer.modmail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.R;

public class ModMailActivity extends AppCompatActivity implements ModMailView{

    private EditText email;
    private EditText oldPassword;
    private Button submit;
    private Button back;
    private String em;
    private String pw;

    private ModMailPres presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modmail);

        email = findViewById(R.id.newMail);
        oldPassword = findViewById(R.id.oldPassword);
        submit = findViewById(R.id.ok);
        back = findViewById(R.id.annulla);
        presenter = new ModMailPresenter(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = email.getText().toString();
                pw = oldPassword.getText().toString();
                if(presenter.validateFields(em,pw)){
                    presenter.reAutenticate(em,pw);
                }
            }
        });


    }


    @Override
    public void modMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public ModMailPres getPresenter(){
        return presenter;
    }

}
