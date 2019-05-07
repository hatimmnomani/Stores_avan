package com.example.stores_avan.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.stores_avan.Entities.Catagory;

@Dao
public interface catDao {
    @Insert
    void insertAll(Catagory[] catList);

    @Query("SELECT * from Catagory")
    Catagory[] getCatList();

    @Query("DELETE FROM Catagory")
    void deleteAllCat();

    @Query("SELECT cid From Catagory WHERE cname = :sname")
    Integer getCatID(String sname);
}
