package com.example.stores_avan.server;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.stores_avan.Entities.Department;
import com.google.gson.Gson;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Response;

public class DeptService extends IntentService {
    public static final String TAG = DeptService.class.getSimpleName();

    public DeptService (){
        super(TAG);
    }

    public DeptService (String name){
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "==> Service is being called");
        //String email = intent.getStringExtra("email");
        Log.d(TAG, "Calling the API for departments ");
        Call<Department[]> call = apiClient
                .getRetrofit()
                .getDepartments();

        try {
            // using the same thread as that of the service.
            Response<Department[]> result = call.execute();
            if (result.code() == HttpsURLConnection.HTTP_OK) {
                Department[] depts = result.body();
                Intent returningIntent = new Intent("com.example.intent.filter");
                String profileJson = new Gson().toJson(depts);
                returningIntent.putExtra("data", profileJson);
                sendBroadcast(returningIntent);
            } else {
                Log.e(TAG,"Some http error" + result.code()
                        + " " + result.message());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
