package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MyProfileActivity extends AppCompatActivity {
    private TextView fullName, email, city, state, phoneNumber;
    FirebaseAuth fAuth;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        initViews();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                
                if(userProfile != null){
                    String Name = userProfile.name;
                    String Email = userProfile.emailAdress;
                    String Number = userProfile.num.trim();

                    fullName.setText(Name);
                    email.setText(Email);
                    phoneNumber.setText(Number);
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(MyProfileActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initViews(){
        fullName = findViewById(R.id.textFullname);
        email = findViewById(R.id.textEmailID);
        phoneNumber = findViewById(R.id.textPhoneNumber);
        city = findViewById(R.id.textCity);
        state = findViewById(R.id.textState);
    }
}