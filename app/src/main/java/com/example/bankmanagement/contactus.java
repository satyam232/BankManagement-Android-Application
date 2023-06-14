package com.example.bankmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bankmanagement.databinding.ActivityContactusBinding;

public class contactus extends DrawerBase {
    ActivityContactusBinding activityContactusBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        activityContactusBinding=ActivityContactusBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Contact Us");
        setContentView(activityContactusBinding.getRoot());
    }
}