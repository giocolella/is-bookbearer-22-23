package com.example.bookbearer.modpass;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.R;

public class ModPassActivity extends AppCompatActivity implements ModPassView{

    private EditText newPass;
    private EditText oldPassword;
    private Button submit;
    private Button back;
    private String np;
    private String pw;
    private ModPassPres presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modpass);

        newPass = findViewById(R.id.newPass);
        oldPassword = findViewById(R.id.oldP);
        submit = findViewById(R.id.okP);
        back = findViewById(R.id.annullaP);
        presenter = new ModPassPresenter(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                np = newPass.getText().toString();
                pw = oldPassword.getText().toString();
                if(presenter.validateFields(np,pw)){
                    presenter.reAutenticate(np,pw);
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

    public ModPassPres getPresenter(){
        return presenter;
    }

}
