package com.example.stores_avan;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//@Entity(tableName = "departments")
class Department {
    public int dId;
    public String dname;
    public int hodId;
}
