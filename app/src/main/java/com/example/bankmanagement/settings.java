package com.example.bankmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankmanagement.databinding.ActivitySettingsBinding;

public class settings extends DrawerBase {
    ActivitySettingsBinding activitySettingsBinding;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
        TextView ok=findViewById(R.id.ok);

        AlertDialog.Builder builder3 = new AlertDialog.Builder(settings.this);
        builder3.setTitle("change PIN");

        View nview = getLayoutInflater().inflate(R.layout.changepin, null);
        Button btn = nview.findViewById(R.id.button);
        Button btn1=nview.findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText crrpin = nview.findViewById(R.id.editTextNumber);
                EditText newpin = nview.findViewById(R.id.editTextNumber2);
                EditText cnfpin = nview.findViewById(R.id.editTextNumber3);
                SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                long value=sp.getInt("confirmpin",0);
                if(!(crrpin.getText().toString()).equals("") && !(newpin.getText().toString()).equals("") && !(cnfpin.getText().toString()).equals("")) {
                    if (value == Integer.parseInt(crrpin.getText().toString())) {
                        if ((newpin.getText().toString()).equals(cnfpin.getText().toString())) {
                            editor.putInt("confirmpin", Integer.parseInt(String.valueOf(cnfpin.getText())));
                            editor.apply();
                            crrpin.setText("");
                            newpin.setText("");
                            cnfpin.setText("");
                            Toast.makeText(settings.this, "PIN Successfully Changed", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(settings.this, "confirm pin is not matching", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(settings.this, "wrong current pin", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(settings.this, "fill all fields", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        builder3.setView(nview);
        dialog=builder3.create();
        dialog.setCanceledOnTouchOutside(false);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });






    }







}