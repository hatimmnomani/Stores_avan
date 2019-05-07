package com.example.stores_avan.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.stores_avan.Entities.Items;

@Dao
public interface itemDao {
    @Insert
    void insertAll(Items[] itemsList);

    @Query("SELECT * from Items")
    Items[] getItemList();

    @Query("DELETE FROM Items")
    void deleteAllItem();

    @Query("SELECT pid From Items WHERE pname = :sname")
    Integer getItemID(String sname);
}
