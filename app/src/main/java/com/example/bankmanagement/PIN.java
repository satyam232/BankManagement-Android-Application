package com.example.bankmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PIN extends AppCompatActivity {
    EditText enterpin;
    TextView topname, forgotpin;
    Button enter;
    ProgressDialog progressdialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pin);
        enterpin=findViewById(R.id.enterpin);
        topname=findViewById(R.id.topname);
        enter=findViewById(R.id.enter);

        forgotpin=findViewById(R.id.forgotpin);


        SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
        String show=sp.getString("name","");
        if(sp.contains("account")){
            topname.setText("Hii "+show);
        }

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog=new ProgressDialog(PIN.this);
                progressdialog.show();
                progressdialog.setContentView(R.layout.progress_bar);
                progressdialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                if(!String.valueOf(enterpin.getText()).equals("")){
                int a= Integer.parseInt(String.valueOf(enterpin.getText()));
                int b=sp.getInt("confirmpin",0);

                    if (b == a) {
                        Intent intent = new Intent(PIN.this, mainpage.class);
                        startActivity(intent);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressdialog.dismiss();
                            }

                        },3000);
                        finish();


                    } else {
                        Toast.makeText(getApplicationContext(), "incorrect PIN", Toast.LENGTH_SHORT).show();
                        progressdialog.dismiss();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "enter your pin", Toast.LENGTH_SHORT).show();
                    progressdialog.dismiss();
                }


            }
        });


        forgotpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.clear();
                ed.apply();
                Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();

            }
        });


    }


}






