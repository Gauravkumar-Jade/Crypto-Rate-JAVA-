package com.jit.cryptoratejava.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jit.cryptoratejava.data.Datum;

import java.util.List;

@Dao
public interface CryptoDao {

    @Insert()
    void insertData(List<Datum> data);

    @Query("DELETE FROM Datum")
    void deleteData();

    @Query("SELECT * FROM Datum")
    List<Datum> getCrypto();
}
