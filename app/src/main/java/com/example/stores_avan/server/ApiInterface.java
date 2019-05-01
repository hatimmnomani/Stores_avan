package com.example.stores_avan.server;
import com.example.stores_avan.Entities.Catagory;
import com.example.stores_avan.Entities.Department;
import com.example.stores_avan.Entities.ProcurementRequest;
import com.example.stores_avan.Entities.userId;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
