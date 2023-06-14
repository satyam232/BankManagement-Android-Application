package com.example.bankmanagement;

import android.graphics.ColorSpace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface myapi{
    @GET("fetchdata.php")
    Call<List<Datum>>getmodels();

}
