package com.example.bankmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  AccountBalance extends AppCompatActivity {
    private TextView rs;
    ProgressDialog progressdialog;

    private static final url url1=new url();
    private static final String url= url1.url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_balance);

        rs=findViewById(R.id.rs);


        progressdialog=new ProgressDialog(AccountBalance.this);
        progressdialog.show();
        progressdialog.setContentView(R.layout.progress_bar);
        progressdialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myapi api=retrofit.create(myapi.class);

        Call<List<Datum>> call=api.getmodels();

        call.enqueue((new Callback<List<Datum>>() {
            @Override
            public void onResponse(Call<List<Datum>> call, Response<List<Datum>> response) {
                List<Datum> data=response.body();
                for(int i=0;i<data.size();i++){
                    SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);

                    Long value=sp.getLong("account",0);
                    long a = Long.parseLong(data.get(i).getAccountno());
                    if(value==a){
                        rs.append(data.get(i).getBalance());
                        progressdialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Datum>> call, Throwable t) {


            }
        })
        );


    }



}