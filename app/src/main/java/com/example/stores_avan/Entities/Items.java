package com.example.stores_avan.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Items {
    @PrimaryKey
    public Integer pid;
    public Integer rid;
    public String pname;
    public String pdisc;
    public Integer quantity;
    public Integer price;
    public Integer m_u;
}
