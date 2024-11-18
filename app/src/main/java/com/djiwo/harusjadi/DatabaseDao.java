package com.djiwo.harusjadi;

import android.arch.lifecycle.LiveData;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.djiwo.harusjadi.database.DataBaseModel;

import java.util.List;

public interface DatabaseDao {

    //untuk get semua data
    @Query("SELECT * FROM tbl_catering where uid != 1")
    LiveData<List<DataBaseModel.DatabaseModel>> getAllOrder();

    //untuk login
    @Query("SELECT * FROM tbl_catering where username= :username AND password= :password")
    LiveData<List<DataBaseModel.DatabaseModel>> getUserByName(String username, String password);

    //untuk insert data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(DataBaseModel.DatabaseModel... modelDatabases);

    //untuk update data
    @Query("UPDATE tbl_catering SET nama_menu= :nama_menu, jml_items= :jml_items, harga= :harga WHERE uid = :uid")
    void updateData(String nama_menu, int jml_items, int harga, int uid);

    //untuk delete data by Id
    @Query("DELETE FROM tbl_catering WHERE uid= :uid")
    void deleteSingleData(int uid);
}

