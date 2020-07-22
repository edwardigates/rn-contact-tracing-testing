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
import moh.gov.il.specialble.bt.Event;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Event> __insertionAdapterOfEvent;

  private final EntityDeletionOrUpdateAdapter<Event> __deletionAdapterOfEvent;

  private final EntityDeletionOrUpdateAdapter<Event> __updateAdapterOfEvent;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public EventDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEvent = new EntityInsertionAdapter<Event>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `events` (`id`,`timestamp`,`device_name`,`action_type`,`success`,`errorMessage`,`battery`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Event value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getTimestamp());
        if (value.getDeviceName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDeviceName());
        }
        if (value.getActionType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getActionType());
        }
        if (value.getSuccess() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSuccess());
        }
        if (value.getErrorMessage() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getErrorMessage());
        }
        stmt.bindLong(7, value.getBattery());
      }
    };
    this.__deletionAdapterOfEvent = new EntityDeletionOrUpdateAdapter<Event>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `events` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Event value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfEvent = new EntityDeletionOrUpdateAdapter<Event>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `events` SET `id` = ?,`timestamp` = ?,`device_name` = ?,`action_type` = ?,`success` = ?,`errorMessage` = ?,`battery` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Event value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getTimestamp());
        if (value.getDeviceName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDeviceName());
        }
        if (value.getActionType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getActionType());
        }
        if (value.getSuccess() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSuccess());
        }
        if (value.getErrorMessage() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getErrorMessage());
        }
        stmt.bindLong(7, value.getBattery());
        stmt.bindLong(8, value.getId());
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM events";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final Event... events) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEvent.insert(events);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final Event event) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEvent.insert(event);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Event event) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfEvent.handle(event);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Event event) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfEvent.handle(event);
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
  public List<Event> getAllEvents() {
    final String _sql = "SELECT * FROM events";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfDeviceName = CursorUtil.getColumnIndexOrThrow(_cursor, "device_name");
      final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "action_type");
      final int _cursorIndexOfSuccess = CursorUtil.getColumnIndexOrThrow(_cursor, "success");
      final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
      final int _cursorIndexOfBattery = CursorUtil.getColumnIndexOrThrow(_cursor, "battery");
      final List<Event> _result = new ArrayList<Event>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Event _item;
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        final String _tmpDeviceName;
        _tmpDeviceName = _cursor.getString(_cursorIndexOfDeviceName);
        final String _tmpActionType;
        _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
        final String _tmpSuccess;
        _tmpSuccess = _cursor.getString(_cursorIndexOfSuccess);
        final String _tmpErrorMessage;
        _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
        final int _tmpBattery;
        _tmpBattery = _cursor.getInt(_cursorIndexOfBattery);
        _item = new Event(_tmpTimestamp,_tmpDeviceName,_tmpActionType,_tmpSuccess,_tmpErrorMessage,_tmpBattery);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Event> getEventsByActionType(final String actionType) {
    final String _sql = "SELECT * FROM events WHERE action_type = ? ORDER BY timestamp desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (actionType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, actionType);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfDeviceName = CursorUtil.getColumnIndexOrThrow(_cursor, "device_name");
      final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "action_type");
      final int _cursorIndexOfSuccess = CursorUtil.getColumnIndexOrThrow(_cursor, "success");
      final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
      final int _cursorIndexOfBattery = CursorUtil.getColumnIndexOrThrow(_cursor, "battery");
      final List<Event> _result = new ArrayList<Event>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Event _item;
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        final String _tmpDeviceName;
        _tmpDeviceName = _cursor.getString(_cursorIndexOfDeviceName);
        final String _tmpActionType;
        _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
        final String _tmpSuccess;
        _tmpSuccess = _cursor.getString(_cursorIndexOfSuccess);
        final String _tmpErrorMessage;
        _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
        final int _tmpBattery;
        _tmpBattery = _cursor.getInt(_cursorIndexOfBattery);
        _item = new Event(_tmpTimestamp,_tmpDeviceName,_tmpActionType,_tmpSuccess,_tmpErrorMessage,_tmpBattery);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
