package com.example.bankmanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private EditText addharno, password, name, phoneno, accountno;
    private TextView textView2;
    private Button button2;
    static private final url url1=new url();
    ProgressDialog progressdialog;
    //private static final String url="https://garni-alignment.000webhostapp.com/banksystem/signup.php";
    private static final String url= url1.url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        button2=findViewById(R.id.signup);
        textView2=findViewById(R.id.textView2);
        addharno=findViewById(R.id.addharno);
        name=findViewById(R.id.name);
        phoneno=findViewById(R.id.phoneno);
        accountno=findViewById(R.id.acountno);
        password=findViewById(R.id.password);


        new execute().execute();









    }



    public class execute extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {


            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this,LoginPage.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
            });


            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressdialog=new ProgressDialog(MainActivity.this);
                    progressdialog.show();
                    progressdialog.setContentView(R.layout.progress_bar);
                    progressdialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent
                    );
                    String account= accountno.getText().toString();
                    String naam=name.getText().toString();
                    String addhar=addharno.getText().toString();
                    String phone=phoneno.getText().toString();
                    String paswd=password.getText().toString();

                    if(!account.equals("") && !naam.equals("") && !addhar.equals("") && !phone.equals("") && !paswd.equals("")){

                        //Start ProgressBar first (Set visibility VISIBLE)
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters

                                String[] field = new String[5];
                                field[0] = "accountno";
                                field[1] = "name";
                                field[2] = "addharno";
                                field[3] = "phoneno";
                                field[4] = "password";
                                //Creating array for data
                                String[] data = new String[5];
                                data[0] = account;
                                data[1] = naam;
                                data[2] = addhar;
                                data[3] = phone;
                                data[4] = paswd;


                                PutData putData = new PutData(url+"signup.php", "POST", field, data);

                                if (putData.startPut()) {

                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if(result.equals("Sign Up Success")){
                                            Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(MainActivity.this,LoginPage.class);
                                            startActivity(intent);
                                            finish();
                                            progressdialog.dismiss();

                                        }
                                        else{
                                            Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                                            progressdialog.dismiss();
                                        }


                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(), "enter all fields", Toast.LENGTH_SHORT).show();
                        progressdialog.dismiss();
                    }


                }
            });







            return null;
        }
    }





}
