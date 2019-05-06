package com.example.stores_avan.server;
import android.arch.persistence.room.Ignore;

import com.example.stores_avan.Entities.Catagory;
import com.example.stores_avan.Entities.Department;
import com.example.stores_avan.Entities.Items;
import com.example.stores_avan.Entities.ProcurementRequest;
import com.example.stores_avan.Entities.userId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/stores_procurement/app/request.php")
    Call<ProcurementRequest[]> getRequests(@Query("eid") Integer eid);

    @GET("/stores_procurement/app/emailauth.php")
    Call<userId> getIdByE(@Query("pemail") String email);

    @GET("/stores_procurement/app/nameauth.php")
    Call<userId> getIdByN(@Query("fname") String fname,@Query("lname") String lname);

    @GET("/stores_procurement/app/dept.php")
    Call<Department[]> getDepartments();

    @GET("/stores_procurement/app/catagory.php")
    Call<Catagory[]> getCat();

    @FormUrlEncoded
    @POST("/stores_procurement/app/post_procurement.php")
    Call<Object> insertRequest(
            @Field("empid") int eid,
            @Field("pur") String purpose,
            @Field("reqd") String reqD,
            @Field("urj")int priority,
            @Field("iName") String pname,
            @Field("itd") String pdisc,
            @Field("quantity") int quantity,
            @Field("price") int price,
            @Field("uom") int m_u);

}
