package moh.gov.il.specialble.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import moh.gov.il.crypto.Contact;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ContactDao_Impl implements ContactDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Contact> __insertionAdapterOfContact;

  private final EntityDeletionOrUpdateAdapter<Contact> __deletionAdapterOfContact;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteContactHistory;

  public ContactDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContact = new EntityInsertionAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Contacts` (`id`,`ephemeral_id`,`rssi`,`timestamp`,`geohash`,`user_id`,`lat`,`lon`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getId());
        if (value.getEphemeral_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getEphemeral_id());
        }
        if (value.getRssi() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindBlob(3, value.getRssi());
        }
        stmt.bindLong(4, value.getTimestamp());
        if (value.getGeohash() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindBlob(5, value.getGeohash());
        }
        if (value.getUser_id() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindBlob(6, value.getUser_id());
        }
        stmt.bindDouble(7, value.getLat());
        stmt.bindDouble(8, value.getLon());
      }
    };
    this.__deletionAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Contacts` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Contacts";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteContactHistory = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Contacts where timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Contact contact) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact.insert(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final Contact... contacts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact.insert(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Contact contact) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfContact.handle(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Contact... contacts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfContact.handleMultiple(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearAll.release(_stmt);
    }
  }

  @Override
  public void deleteContactHistory(final int history) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteContactHistory.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, history);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteContactHistory.release(_stmt);
    }
  }

  @Override
  public List<Contact> getAllContacts() {
    final String _sql = "SELECT * FROM Contacts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfEphemeralId = CursorUtil.getColumnIndexOrThrow(_cursor, "ephemeral_id");
      final int _cursorIndexOfRssi = CursorUtil.getColumnIndexOrThrow(_cursor, "rssi");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfGeohash = CursorUtil.getColumnIndexOrThrow(_cursor, "geohash");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
      final int _cursorIndexOfLon = CursorUtil.getColumnIndexOrThrow(_cursor, "lon");
      final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Contact _item;
        _item = new Contact();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final byte[] _tmpEphemeral_id;
        _tmpEphemeral_id = _cursor.getBlob(_cursorIndexOfEphemeralId);
        _item.setEphemeral_id(_tmpEphemeral_id);
        final byte[] _tmpRssi;
        _tmpRssi = _cursor.getBlob(_cursorIndexOfRssi);
        _item.setRssi(_tmpRssi);
        final int _tmpTimestamp;
        _tmpTimestamp = _cursor.getInt(_cursorIndexOfTimestamp);
        _item.setTimestamp(_tmpTimestamp);
        final byte[] _tmpGeohash;
        _tmpGeohash = _cursor.getBlob(_cursorIndexOfGeohash);
        _item.setGeohash(_tmpGeohash);
        final byte[] _tmpUser_id;
        _tmpUser_id = _cursor.getBlob(_cursorIndexOfUserId);
        _item.setUser_id(_tmpUser_id);
        final double _tmpLat;
        _tmpLat = _cursor.getDouble(_cursorIndexOfLat);
        _item.setLat(_tmpLat);
        final double _tmpLon;
        _tmpLon = _cursor.getDouble(_cursorIndexOfLon);
        _item.setLon(_tmpLon);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Cursor getCursorAll() {
    final String _sql = "SELECT * FROM Contacts order by id asc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _tmpResult = __db.query(_statement);
    return _tmpResult;
  }
}
