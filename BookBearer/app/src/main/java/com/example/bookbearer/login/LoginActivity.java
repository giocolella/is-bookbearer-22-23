package com.example.bookbearer.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.CatalogueActivity;
import com.example.bookbearer.MainActivity;
import com.example.bookbearer.R;

public class LoginActivity extends AppCompatActivity implements LogView {

    private EditText email;
    private EditText pass;
    private Button logButton;
    private LogPresenter presenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.emailText);
        pass = findViewById(R.id.passwordText);

        logButton = findViewById(R.id.log);

        presenter = new LoginPresenter(this);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uMail = email.getText().toString();
                String uPass = pass.getText().toString();
                if(presenter.validateLogin(uMail,uPass)){
                    presenter.checkCataloghist(uMail,uPass);
                }
            }
        });

    }

    @Override
    public void regMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void StartActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void startCatalogueActivity(String uMail) {
        Intent in = new Intent(LoginActivity.this, CatalogueActivity.class);
        in.putExtra("Mail",uMail);
        startActivity(in);
        finish();
    }

    public LogPresenter getPresenter(){
        return presenter;
    }

}
