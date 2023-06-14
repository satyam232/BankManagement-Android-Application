package com.example.bankmanagement;

import static java.lang.Long.getLong;
import static java.lang.Long.parseLong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferAmount extends AppCompatActivity {
    EditText amttoransfer, yourpswd, acno;
    ProgressBar pr1;
    static String acno1;
    static String amttoransfer1;
    static String yourpswd1;
    static private final url url1=new url();
    private static String url_process = url1.url;

    ProgressDialog progressdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_amount);

        amttoransfer = findViewById(R.id.amttransfer);
        acno = findViewById(R.id.acno);
        yourpswd = findViewById(R.id.yourpswd);
        Button btn2 = findViewById(R.id.button2);
        pr1 = findViewById(R.id.p1);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                amttoransfer1 = amttoransfer.getText().toString();
                acno1 = acno.getText().toString();
                yourpswd1 = yourpswd.getText().toString();
                progressdialog = new ProgressDialog(TransferAmount.this);
                progressdialog.show();
                progressdialog.setContentView(R.layout.progress_bar);
                progressdialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (isConnected) {

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!amttoransfer1.equals("") && !acno1.equals("") && !yourpswd1.equals("")) {
                                SharedPreferences sp = getSharedPreferences("rana", MODE_PRIVATE);
                                String value = sp.getString("password", "");
                                long value2 = sp.getLong("account", 0);
                                long a = Long.parseLong(acno1);
                                if (value2 == a) {
                                    Toast.makeText(getApplicationContext(), "you can't transfer to yourself", Toast.LENGTH_SHORT).show();
                                    progressdialog.dismiss();

                                } else {
                                    if (yourpswd1.equals(value)) {
                                        process();
                                        amttoransfer.setText("");
                                        acno.setText("");
                                        yourpswd.setText("");

                                    } else {
                                        Toast.makeText(getApplicationContext(), "incorrect password", Toast.LENGTH_SHORT).show();
                                        progressdialog.dismiss();
                                    }
                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "fill the required field", Toast.LENGTH_SHORT).show();
                                progressdialog.dismiss();
                            }


                        }
                    }, 500);

                } else {
                    Toast.makeText(TransferAmount.this, "please connect to the network", Toast.LENGTH_SHORT).show();
                    progressdialog.dismiss();
                }

            }
        });


    }


    void process() {
        String amt1 = amttoransfer.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, url_process+"testone.php", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                if (response.equals("Data updated")) {
                    Toast.makeText(TransferAmount.this, response, Toast.LENGTH_SHORT).show();
                    progressdialog.setTitle("successfully Transfered!!!");
                    progressdialog.dismiss();


                } else {
                    Toast.makeText(TransferAmount.this, response, Toast.LENGTH_SHORT).show();

                    progressdialog.dismiss();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressdialog.setMessage("transfered fails");
                Toast.makeText(TransferAmount.this, "Transfered Failed", Toast.LENGTH_SHORT).show();
                progressdialog.dismiss();

            }
        }) {

            @Override

            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                SharedPreferences sp = getSharedPreferences("rana", MODE_PRIVATE);
                Long value = sp.getLong("account", 0);
                params.put("accountno", acno1);
                params.put("accountno1", String.valueOf(value));
                params.put("Balance", amt1);


                return params;
            }


        };
        RequestQueue requestQueue = Volley.newRequestQueue(TransferAmount.this);
        requestQueue.add(request);


    }
}