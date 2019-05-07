package com.example.stores_avan.dao;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.example.stores_avan.Entities.Catagory;
import com.example.stores_avan.Entities.Department;
import com.example.stores_avan.Entities.Inventory;
import com.example.stores_avan.Entities.Items;
import com.example.stores_avan.Entities.ProcurementRequest;

@Database(
        entities = {
                Department.class,
                Catagory.class,
                ProcurementRequest.class,
                Inventory.class,
                Items.class
        },
        version = 6,
        exportSchema = false
)
public abstract class StoresDB extends RoomDatabase {
    public abstract deptDao deptDao();
    public abstract procDao procDao();
    public abstract catDao catDao();
    public abstract itemDao itemDao();
    public abstract invenDao invenDao();
}
