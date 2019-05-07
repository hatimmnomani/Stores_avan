package com.example.stores_avan.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.stores_avan.Entities.ProcurementRequest;

@Dao
public interface procDao {
    @Insert
    void insertAll(ProcurementRequest[] procList);

    @Query("SELECT * from ProcurementRequest")
    ProcurementRequest[] getprocList();

    @Query("SELECT * from ProcurementRequest where status < 0 or status >3")
    ProcurementRequest[] getprocHisList();

    @Query("SELECT * from ProcurementRequest where status >= 0 or status <4")
    ProcurementRequest[] getprocStatusList();

    @Query("DELETE FROM ProcurementRequest")
    void deleteAllproc();

    @Query("SELECT rid From ProcurementRequest WHERE purpose = :sname")
    Integer getprocID(String sname);
}
