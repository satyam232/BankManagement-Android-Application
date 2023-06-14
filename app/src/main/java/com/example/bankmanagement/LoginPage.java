package com.example.bankmanagement;


import static java.lang.Long.parseLong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class LoginPage extends AppCompatActivity {

    private EditText accountnoln,passwordln;
    private TextView editText;
    private Button login;
    ProgressDialog progressdialog;

    static private final url url1=new url();
    final private static String url= url1.url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //getSupportActionBar().hide();
        accountnoln=findViewById(R.id.acountnoln);
        passwordln=findViewById(R.id.passwordln);
        login=findViewById(R.id.login);
        editText=findViewById(R.id.textView3);
        pinnotcreated();
        cheakexistance();
        new getUserDatarequest().execute();


    }

    public class getUserDatarequest extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(LoginPage.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();

                }
            });


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                    if (isConnected) {

                        progressdialog = new ProgressDialog(LoginPage.this);
                        progressdialog.show();
                        progressdialog.setContentView(R.layout.progress_bar);
                        progressdialog.getWindow().setBackgroundDrawableResource(
                                android.R.color.transparent
                        );

                        String accountno2 = accountnoln.getText().toString();
                        String password2 = passwordln.getText().toString();

                        if (!accountno2.equals("") && !password2.equals("")) {
                            //Start ProgressBar first (Set visibility VISIBLE)
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    String[] field = new String[2];
                                    field[0] = "accountno";
                                    field[1] = "password";


                                    String[] data = new String[2];
                                    data[0] = accountno2;
                                    data[1] = password2;
                                    PutData putData = new PutData(url+"login.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            String result = putData.getResult();
                                            if (result.equals("Login Success")) {
                                                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                                SharedPreferences sp = getSharedPreferences("rana", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sp.edit();
                                                editor.putLong("account", parseLong(accountnoln.getText().toString()));
                                                editor.putString("password", passwordln.getText().toString());
                                                editor.apply();
                                                Intent intent = new Intent(getApplicationContext(), createPIN.class);
                                                startActivity(intent);
                                                finish();
                                                progressdialog.dismiss();

                                            } else {
                                                Toast.makeText(getApplicationContext(), "incorrect accountno/password", Toast.LENGTH_SHORT).show();
                                                progressdialog.dismiss();
                                            }
                                        } else {
                                            Toast.makeText(getApplicationContext(), "try again", Toast.LENGTH_SHORT).show();
                                            progressdialog.dismiss();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "pehle wale me hi nahi ja pa raha hai", Toast.LENGTH_SHORT).show();
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                        } else {
                            Toast.makeText(LoginPage.this, "fill the details", Toast.LENGTH_SHORT).show();
                            progressdialog.dismiss();
                        }
                    }else{
                        Toast.makeText(LoginPage.this, "please connect to the network", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            return null;
        }


    }
    public  void cheakexistance(){
        SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
        if(sp.contains("account")){
            Intent intent=new Intent(getApplicationContext(),PIN.class);
            startActivity(intent);
            finish();
        }

    }

    public void pinnotcreated(){
        SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
        int show=sp.getInt("confirmpin",0);
        if(show==0){
            SharedPreferences.Editor ed=sp.edit();
            ed.clear();
            ed.apply();
        }
    }




}