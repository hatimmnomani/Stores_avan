package com.example.stores_avan.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.stores_avan.Entities.Inventory;

@Dao
public interface invenDao {
    @Insert
    void insertAll(Inventory[] inventoryList);

    @Query("SELECT * from Inventory")
    Inventory[] getInventoryList();

    @Query("DELETE FROM Inventory")
    void deleteAllInven();

    @Query("SELECT shelf_id From Inventory WHERE stock_name = :sname")
    Integer getInventoryID(String sname);
}
