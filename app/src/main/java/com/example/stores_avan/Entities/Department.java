package com.example.stores_avan.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "departments")
public class Department {
    @PrimaryKey
    public Integer dId;
    public String dname;
    public Integer hodId;
}
