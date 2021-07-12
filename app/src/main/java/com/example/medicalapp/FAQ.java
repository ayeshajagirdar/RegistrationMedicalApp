package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FAQ extends AppCompatActivity {
private ImageView imgfaq;
private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        imgfaq = findViewById(R.id.imageView);
        text = findViewById(R.id.textupdatesoon);


    }
}