package moh.gov.il.specialble.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import moh.gov.il.crypto.Contact;

import java.util.List;

/**
 * Created by hagai on 11/05/2020.
 */

@Dao
public interface ContactDao
{

    @Query("SELECT * FROM Contacts")
    List<Contact> getAllContacts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Contact... contacts);

    @Delete
    void delete(Contact contact);

    @Delete
    void delete(Contact... contacts);

    @Query("DELETE FROM Contacts")
    public void clearAll();

    @Query("SELECT * FROM Contacts order by id asc")
    Cursor getCursorAll();

    @Query("DELETE FROM Contacts where timestamp < :history")
    public void deleteContactHistory(int history);

}
