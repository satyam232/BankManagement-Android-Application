package com.example.bankmanagement;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import com.example.bankmanagement.databinding.ActivityMainpageBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mainpage extends DrawerBase{


    TextView profile,accountbalance,transferamount,loanservices,showname;
    AlertDialog.Builder builder;

    ActivityMainpageBinding activityMainpageBinding;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainpageBinding=ActivityMainpageBinding.inflate(getLayoutInflater());
        setContentView(activityMainpageBinding.getRoot());

        profile=findViewById(R.id.profile);
        accountbalance=findViewById(R.id.accountbalance);
        transferamount=findViewById(R.id.transferammount);
        loanservices=findViewById(R.id.loanservices);

        showname=findViewById(R.id.showname);
        SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
        String naam=sp.getString("name","");

            showname.setText("Welcome "+naam);













        allpages();







    }



    public void allpages(){
        transferamount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),TransferAmount.class);
                startActivity(intent);
            }
        });




        loanservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoanServices.class);
                startActivity(intent);

            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),AccountInfo.class);
                startActivity(intent);


            }

        });


        accountbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mainpage.this,AccountBalance.class);
                startActivity(intent);

            }
        });



    }












}