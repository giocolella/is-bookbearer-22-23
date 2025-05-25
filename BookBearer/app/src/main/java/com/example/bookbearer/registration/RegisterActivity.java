package com.example.bookbearer.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.MainActivity;
import com.example.bookbearer.R;
import com.example.bookbearer.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegView{

    private EditText nomeUtente;
    private EditText email;
    private EditText pass;
    private EditText confPass;
    private Button regButton;
    private RegPresenter presenter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        presenter = new RegistrationPresenter(this);

        nomeUtente = findViewById(R.id.regUsername);
        email = findViewById(R.id.regMail);
        pass = findViewById(R.id.regPass);
        confPass = findViewById(R.id.regConfPass);
        regButton = findViewById(R.id.reg);

        regButton.setOnClickListener(regButtonListener);

    }

    View.OnClickListener regButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String uName = nomeUtente.getText().toString();
            boolean valReg = presenter.validateRegistration(uName,email.getText().toString(),pass.getText().toString(),confPass.getText().toString());
            if(valReg){
                presenter.registerAccount(uName,email.getText().toString(),pass.getText().toString());
            }
        }
    };

    @Override
    public void regMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToLogin() {
        regMessage("Registrazione completata");
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    public RegPresenter getPresenter(){
        return presenter;
    }

}