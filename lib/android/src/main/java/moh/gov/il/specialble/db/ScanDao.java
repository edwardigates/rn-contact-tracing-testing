package moh.gov.il.specialble.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import moh.gov.il.specialble.bt.Scan;

import java.util.List;


@Dao
public interface ScanDao {

    @Query("SELECT * FROM scans")
    List<Scan> getAllBLEScans();

    @Query("SELECT * FROM scans WHERE publicKey = :publicKey ORDER BY timestamp desc")
    List<Scan> getScansByKey(String publicKey);

    @Insert
    void insertAll(Scan... scans);

    @Update
    void update(Scan scan);

    @Insert
    void insert(Scan scan);

    @Delete
    void delete(Scan scan);

    @Query("DELETE FROM scans")
    public void clearAll();
}
