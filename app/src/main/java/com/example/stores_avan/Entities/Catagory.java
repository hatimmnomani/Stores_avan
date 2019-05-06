package com.example.stores_avan.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Catagory {
    @PrimaryKey
    public Integer cId;
    public String cname;

}
