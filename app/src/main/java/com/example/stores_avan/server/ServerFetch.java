package com.example.stores_avan.server;

import android.app.IntentService;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.stores_avan.Entities.Catagory;
import com.example.stores_avan.Entities.Department;
import com.example.stores_avan.Entities.Inventory;
import com.example.stores_avan.Entities.ProcurementRequest;
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
                break;
            }
            case "update":{
                Log.d(TAG,"update request for");
                fetchNUpdate();
                break;
            }
            case "getDepList":{
                Log.d(TAG,"get dept request for");
                getDept();
                break;
            }
            case "getProcHisList":{
                Log.d(TAG,"get proc history");
                getProcHis();
                break;
            }
            case "getProcStatusList":{
                Log.d(TAG,"get proc Status");
                getProcStatus();
                break;
            }
        }
        db.close();
    }

    private void getProcHis() {
        ProcurementRequest[] a = db.procDao().getprocHisList();
        Intent returningIntent = new Intent("broadcast.get.History");

        String profileJson = new Gson().toJson(a);
        returningIntent.putExtra("data", profileJson);
        sendBroadcast(returningIntent);
    }
    private void getProcStatus() {
        ProcurementRequest[] a = db.procDao().getprocList();
        Log.d(TAG,"==> in Status value - "+a[0].purpose);
        Intent returningIntent = new Intent("broadcast.get.Status");
        String profileJson = new Gson().toJson(a);
        returningIntent.putExtra("data", profileJson);
        sendBroadcast(returningIntent);
    }

    private void getDept() {
        Department[] a = db.deptDao().getAll();
        Intent returningIntent = new Intent("broadcast.get.Dept");
        String profileJson = new Gson().toJson(a);
        returningIntent.putExtra("data", profileJson);
        sendBroadcast(returningIntent);
    }


    private void fetchNUpdate() {
        final Call<Department[]> dCall = apiClient.getRetrofit().getDepartments();
        final Call<Inventory[]> iCall = apiClient.getRetrofit().getInven();
        final Call<ProcurementRequest[]> pCall = apiClient.getRetrofit().getRequests(2);
        SharedPreferences sharedPreferences = getSharedPreferences("com.avan.stores.userprofile", Context.MODE_PRIVATE);
        int eid = sharedPreferences.getInt("eid",7);
        final Call<Catagory[]> cCall = apiClient.getRetrofit().getCat();
        try{
            Response<Department[]> response1=dCall.execute();
            Response<Inventory[]> response2=iCall.execute();
            Response<ProcurementRequest[]> response3=pCall.execute();
            Response<Catagory[]> response4=cCall.execute();
            if(response1.code()==HttpsURLConnection.HTTP_OK){
                Department[] dept=response1.body();
                db.deptDao().deleteAlldept();
                db.deptDao().insertAll(dept);
                Log.d(TAG,"Inserting updated departments");
                Log.d(TAG,"==> Testing insert "+db.deptDao().getAll()[0].dname);

            }else{
                Log.w(TAG,"HTTP Error for departmens "+response1.code()+" - "+response1.message());
            }
            if(response2.code()==HttpsURLConnection.HTTP_OK){
                Inventory[] inv=response2.body();
                db.invenDao().deleteAllInven();
                db.invenDao().insertAll(inv);
                Log.d(TAG,"Inserting updated Inventory");
                Log.d(TAG,"Testing insert "+db.invenDao().getInventoryList()[0]);

            }else{
                Log.w(TAG,"HTTP Error for inventory "+response2.code()+" - "+response2.message());
            }
            if(response3.code()==HttpsURLConnection.HTTP_OK){
                ProcurementRequest[] proc=response3.body();
                db.procDao().deleteAllproc();
                db.procDao().insertAll(proc);
                Log.d(TAG,"Inserting updated Procurements");
                Log.d(TAG,"Testing insert "+db.procDao().getprocList()[0]);

            }else{
                Log.w(TAG,"HTTP Error for Procurement "+response3.code()+" - "+response3.message());
            }
            if(response4.code()==HttpsURLConnection.HTTP_OK){
                Catagory[] cat=response4.body();
                db.catDao().deleteAllCat();
                db.catDao().insertAll(cat);
                Log.d(TAG,"Inserting updated Inventory");
                Log.d(TAG,"Testing insert "+db.catDao().getCatList()[0]);

            }else{
                Log.w(TAG,"HTTP Error for inventory "+response4.code()+" - "+response4.message());
            }

        }catch (IOException e){
            e.printStackTrace();
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
                    fetchNUpdate();
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
