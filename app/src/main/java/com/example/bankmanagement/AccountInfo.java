package com.example.bankmanagement;

import static java.lang.Long.parseLong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountInfo extends AppCompatActivity {

    private static final url url1=new url();
    private static final String url= url1.url;
    public TextView accountview;
    public static TextView nameview;
    public TextView phoneview;
    public TextView addharview;
    ProgressDialog progressdialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        accountview=findViewById(R.id.accountview);
        nameview=findViewById(R.id.nameview);
        phoneview=findViewById(R.id.phoneview);
        addharview=findViewById(R.id.addharview);




        progressdialog=new ProgressDialog(AccountInfo.this);
        progressdialog.setCanceledOnTouchOutside(true);
        progressdialog.show();
        progressdialog.setContentView(R.layout.progress_bar);
        progressdialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent

        );

        new bg().execute();

    }


    public class bg extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

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
                        SharedPreferences.Editor editor=sp.edit();
                        long value=sp.getLong("account",0);
                        long a = Long.parseLong(data.get(i).getAccountno());
                        if(value==a){
                            nameview.append(" " + data.get(i).getName());
                            editor.putString("name",data.get(i).getName());
                            editor.apply();
                            accountview.append(" " + data.get(i).getAccountno());
                            phoneview.append(" "+data.get(i).getPhoneno());
                            addharview.append(" "+data.get(i).getAddharno());

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressdialog.dismiss();
                                }

                            },0);

                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Datum>> call, Throwable t) {


                }
            }));

            return null;

        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}