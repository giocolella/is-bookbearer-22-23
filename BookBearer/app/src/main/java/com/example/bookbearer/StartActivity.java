package com.example.bookbearer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookbearer.login.LoginActivity;
import com.example.bookbearer.registration.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    private Button register;
    private Button login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        register = findViewById(R.id.registerButton);
        login = findViewById(R.id.loginButton);

        register.setOnClickListener(regListener);
        login.setOnClickListener(logListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            startActivity(new Intent(StartActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }

    }

    View.OnClickListener regListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(StartActivity.this,RegisterActivity.class));
            finish();
        }
    };

    View.OnClickListener logListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(StartActivity.this,LoginActivity.class));
            finish();
        }
    };

}
