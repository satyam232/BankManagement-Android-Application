package com.example.bankmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoanServices extends AppCompatActivity {

    Button submit;
    static TextInputEditText ab;
    EditText editText;
    private static String url="http://192.168.33.131/BankMAnagement/";
    //private static String url="https://garni-alignment.000webhostapp.com/banksystem/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_services);
        //getSupportActionBar().hide();
        submit=findViewById(R.id.submit);
        ab=findViewById(R.id.text);
        editText=findViewById(R.id.editTextText);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt2= Objects.requireNonNull(ab.getText()).toString();
                if(!txt2.equals("")){
                    process1();
                    ab.setText("");
                    editText.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(), "please fill the above form", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    void process1(){
        TextInputEditText a=findViewById(R.id.text);

        StringRequest request = new StringRequest(Request.Method.POST, url+"request.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Data updated")) {


                    Toast.makeText(getApplicationContext(), "request submited succesfully", Toast.LENGTH_SHORT).show();



                } else {
                    Toast.makeText(LoanServices.this, "thank you we will be contacting soon", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoanServices.this, "thank you we will be contacting soon", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override

            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
                Long value=sp.getLong("account",0);

                params.put("accountno", String.valueOf(value));
                params.put("request",a.getText().toString());

                return params;
            }


        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoanServices.this);
        requestQueue.add(request);
    }
}