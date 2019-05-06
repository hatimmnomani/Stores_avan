package com.example.stores_avan.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Inventory {
    @PrimaryKey
    public Integer shelf_id;
    public String stock_name;
    public Integer Quantity;
    public Integer price;
    public String date_in;
    public Integer order_id;
    //@ForeignKey(Department)
    public Integer department_ref;
    //@ForeignKey(Catagory)
    public Integer cid;
}
