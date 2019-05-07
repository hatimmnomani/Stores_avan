package com.example.stores_avan.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.stores_avan.Entities.Items;

import java.util.ArrayList;

@Entity
public class ProcurementRequest {
    @PrimaryKey
    public Integer rid;
    public String purpose;
    public Integer status;
    public String requestedD;
    public String reqD;
    public Integer priority;

    /*@Ignore
    @Override
    public String toString() {
        return ;
    }*/

    @Ignore
    public ArrayList<Items> products;

}
