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
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register, btn;
    private EditText userName,email, password, phone, confirmPassword;
    private Button btnregister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btn.setOnClickListener(this);
        btnregister.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alreadyHaveAnAccount:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.Register:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String name = userName.getText().toString().trim();
        String emailAdress = email.getText().toString().trim();
        String pw = password.getText().toString().trim();
        String cpw = confirmPassword.getText().toString().trim();
        String num = phone.getText().toString().trim();

        if(name.isEmpty()){
            userName.setError("Name is required");
            userName.requestFocus();
            return;
        }
        if(emailAdress.isEmpty()){
            email.setError("Email ID is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailAdress).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if (num.isEmpty()){
            phone.setError("Phone number is required");
            phone.requestFocus();
            return;
        }
        if (num.length() < 10){
            phone.setError("Enter 10 digit number");
            phone.requestFocus();
        }
        if(pw.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(pw.length() < 6){
            password.setError("Min password length should be 6 characters");
            password.requestFocus();
            return;
        }
        if(!cpw.equals(pw)){
            confirmPassword.setError("Confirm password doesn't match");
            confirmPassword.requestFocus();
        }
        mAuth.createUserWithEmailAndPassword(emailAdress, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name, emailAdress, num);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, "Failed to register Try Again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to register Try Again!", Toast.LENGTH_LONG).show();

                        }

                    }
                });
    }

    private void initView() {
        register = findViewById(R.id.logo);
        btn = findViewById(R.id.alreadyHaveAnAccount);
        userName = findViewById(R.id.inputUserName);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.inputConfirmPassword);
        phone = findViewById(R.id.inputPhoneNumber);
        btnregister = findViewById(R.id.Register);
    }
}