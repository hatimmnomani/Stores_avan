package com.example.stores_avan.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.stores_avan.Entities.Department;

import java.util.List;

@Dao
public interface deptDao {
    @Query("SELECT * FROM departments")
    Department[] getAll();

    @Query("SELECT * FROM departments WHERE dId IN (:userIds)")
    Department[] loadAllByIds(int[] userIds);

    @Query("SELECT * FROM departments WHERE dname LIKE :dname  LIMIT 1")
    Department findByName(String dname);

    @Query("SELECT dId FROM departments WHERE dname LIKE :dname  LIMIT 1")
    Integer getIdByName(String dname);

    @Query("DELETE FROM departments")
    void deleteAlldept();

    @Insert
    void insertAll(Department[] users);

    @Delete
    void delete(Department user);
}
