package com.example.stores_avan.server;

import android.app.IntentService;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.stores_avan.Entities.Department;
import com.example.stores_avan.Entities.userId;
import com.example.stores_avan.dao.StoresDB;
import com.google.gson.Gson;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ServerFetch extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */

    public ServerFetch() {
        super(TAG);
    }
    private StoresDB db;

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: of ServerFetch");
        db= Room.databaseBuilder(getApplicationContext(),StoresDB.class,"store.db")
                .fallbackToDestructiveMigration()
                .build();
        String request = intent.getStringExtra("fetch");
        switch (request){
            case "auth":{
                Log.d(TAG,"auth request for"+intent.getStringExtra("email"));
                authenticate(intent.getStringExtra("email"),intent.getStringExtra("name"));
            }
        }
    }

    private void authenticate(String email,String name) {
        Call<userId> call1;
        String[] a= email.split("@");
        if(a[1].equalsIgnoreCase("avantika.edu.in")){
            //String x = a[0];
            Log.d(TAG,"resulting string array - "+ a[0]);
            String b= a[0].substring(0,a[0].indexOf('.'));
            String c = a[0].substring(a[0].indexOf('.')+1);
            Log.d(TAG,"resulting string array - "+ b);
            call1 = apiClient
                    .getRetrofit()
                    .getIdByN(b,c);
        } else{
            call1 = apiClient
                    .getRetrofit()
                    .getIdByE(email);
        }
        try {
            // using the same thread as that of the service.
            Response<userId> result = call1.execute();
            if (result.code() == HttpsURLConnection.HTTP_OK) {
                userId depts = result.body();
                Log.d(TAG, "==> " + depts.first_name);
                Intent returningIntent = new Intent("broadcast.get.authentication");
                if(depts.first_name==null){
                    Log.d(TAG, "==> Failed" );
                    returningIntent.putExtra("failed",true);
                }else{
                    Log.d(TAG, "==> Success" );
                String profileJson = new Gson().toJson(depts);
                returningIntent.putExtra("data", profileJson);
                returningIntent.putExtra("failed",false);}
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
