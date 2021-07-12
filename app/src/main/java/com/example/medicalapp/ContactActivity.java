package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactActivity extends AppCompatActivity {


   private ImageView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        map=findViewById(R.id.google);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("geo:0, 0?q=Ayodhya");
                Intent intent = new Intent(Intent.ACTION_VIEW,url);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);

            }
        });
    }
}