 package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

 public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     private TextView welcomeText;
     private Button btnProfile;
     private DrawerLayout drawer;
     private Toolbar toolbar;
     private ActionBarDrawerToggle Toggle;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         toolbar.setTitleTextColor(getResources().getColor(R.color.white));

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         ActionBarDrawerToggle Toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                 R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer.addDrawerListener(Toggle);
         Toggle.syncState();
         getSupportActionBar().setHomeButtonEnabled(true);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu4);

         NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
         navigationView.setNavigationItemSelectedListener(this);

     }

     @Override
     public void onBackPressed() {
         if (drawer.isDrawerOpen(GravityCompat.START)) {
             drawer.closeDrawer(GravityCompat.START);
         } else {
             super.onBackPressed();
         }

         AlertDialog.Builder alertDailogBuilder = new AlertDialog.Builder(MainActivity.this);
         alertDailogBuilder.setTitle("Confirm Exit..!!");
         alertDailogBuilder.setIcon(R.drawable.ic_exit2);
         alertDailogBuilder.setMessage("Are you sure you want to exit ?");
         alertDailogBuilder.setCancelable(false);
         alertDailogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 finish();
             }
         });
        alertDailogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You clicked on cancel", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = alertDailogBuilder.create();
        alertDialog.show();
     }
     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         if (Toggle.onOptionsItemSelected(item)) {
             return true;
         }
         return super.onOptionsItemSelected(item);
     }

     @Override
     public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
         int id = item.getItemId();
         if (id == R.id.nav_upload) {
             Intent intent = new Intent(MainActivity.this,UploadActivity.class);
             startActivity(intent);
         }
         if (id == R.id.nav_myProfile) {
             Intent intent = new Intent(MainActivity.this,MyProfileActivity.class);
             startActivity(intent);
         }
         if (id == R.id.nav_myorders) {
             Intent intent = new Intent(MainActivity.this,MyOrders.class);
             startActivity(intent);
         }
         if (id == R.id.nav_TermsandConditions) {
             Intent intent = new Intent(MainActivity.this,TermsAndConditionsActivity.class);
             startActivity(intent);
         }
         if (id == R.id.nav_faq) {
             Intent intent = new Intent(MainActivity.this,FAQ.class);
             startActivity(intent);
         }
         if (id == R.id.nav_contact) {
             Intent intent = new Intent(MainActivity.this,ContactActivity.class);
             startActivity(intent);
         }
         if (id == R.id.nav_Logout) {
             Intent intent = new Intent(MainActivity.this,LogoutActivity.class);
             startActivity(intent);
         }
         return false;
     }

 }