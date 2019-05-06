package com.example.stores_avan.server;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.stores_avan.Entities.ProcurementRequest;

import java.io.IOException;

import retrofit2.Call;

import static android.content.ContentValues.TAG;

public class PostService extends IntentService {

    public PostService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"auth request for"+intent.getStringExtra("purpose"));
        String p = intent.getStringExtra("purpose");
        int eid =  intent.getIntExtra("eid",0);
        String pn = intent.getStringExtra("pname");
        String pd = intent.getStringExtra("pdisc");
        int q =  intent.getIntExtra("quantity",0);
        int pr = intent.getIntExtra("price",0);
        String rd = intent.getStringExtra("reqD");
        int m =  intent.getIntExtra("m_u",0);
        int u =  intent.getIntExtra("urj",0);
        Log.d(TAG,"==> post requset for ->"+eid+" "+p+" "+rd+" "+u+" "+pn+" "+pd+" "+q+" "+pr+" "+m+" end ");
        Call<Object> call1 = apiClient
                .getRetrofit()
                .insertRequest(eid,p,rd,u,pn,pd,q,pr,1);
        try {
            call1.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*intentS.putExtra("purpose",E1.getText().toString());
        intentS.putExtra("eid","2");
        intentS.putExtra("pname",E2.getText().toString());
        intentS.putExtra("pdisc",E3.getText().toString());
        intentS.putExtra("quantity",E4.getText().toString());
        intentS.putExtra("price",E5.getText().toString());
        intentS.putExtra("reqD",E6.getText().toString());
        intentS.putExtra("m_u",m_u.getBaseline());
        intentS.putExtra("urj",1);*/
    }
}
