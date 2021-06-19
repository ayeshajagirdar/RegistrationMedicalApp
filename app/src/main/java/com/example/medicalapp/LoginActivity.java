package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
private EditText email, password;
private TextView login, signUp, forgotPassword;
private Button btnlogin;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        signUp.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textsignUp:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.login:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }

    }

    private void userLogin() {
        String emailAdress = email.getText().toString().trim();
        String pw = password.getText().toString().trim();

        if(emailAdress.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailAdress).matches()){
            email.setError("Please enter a valid Email");
            email.requestFocus();
            return;
        }
        if(pw.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(pw.length() < 6){
            password.setError("Min Password length is 6 characters");
            password.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailAdress, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }

            }
        });

        }

    private void initViews() {
        email = findViewById(R.id.inputUserEmail);
        password = findViewById(R.id.inputUserPassword);
        login = findViewById(R.id.logo);
        signUp = findViewById(R.id.textsignUp);
        btnlogin = findViewById(R.id.login);
        forgotPassword = findViewById(R.id.forgotPassword);
    }

}