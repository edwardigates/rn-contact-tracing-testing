package moh.gov.il.specialble.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import moh.gov.il.specialble.bt.Device;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DeviceDao_Impl implements DeviceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Device> __insertionAdapterOfDevice;

  private final EntityDeletionOrUpdateAdapter<Device> __deletionAdapterOfDevice;

  private final EntityDeletionOrUpdateAdapter<Device> __updateAdapterOfDevice;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public DeviceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDevice = new EntityInsertionAdapter<Device>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Device` (`publicKey`,`first_timestamp`,`last_timestamp`,`device_address`,`rssi`,`tx`,`device_protocol`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Device value) {
        if (value.getPublicKey() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPublicKey());
        }
        stmt.bindLong(2, value.getFirstTimestamp());
        stmt.bindLong(3, value.getLastTimestamp());
        if (value.getDeviceAddress() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDeviceAddress());
        }
        stmt.bindLong(5, value.getRssi());
        stmt.bindLong(6, value.getTx());
        if (value.getDeviceProtocol() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDeviceProtocol());
        }
      }
    };
    this.__deletionAdapterOfDevice = new EntityDeletionOrUpdateAdapter<Device>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Device` WHERE `publicKey` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Device value) {
        if (value.getPublicKey() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPublicKey());
        }
      }
    };
    this.__updateAdapterOfDevice = new EntityDeletionOrUpdateAdapter<Device>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Device` SET `publicKey` = ?,`first_timestamp` = ?,`last_timestamp` = ?,`device_address` = ?,`rssi` = ?,`tx` = ?,`device_protocol` = ? WHERE `publicKey` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Device value) {
        if (value.getPublicKey() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPublicKey());
        }
        stmt.bindLong(2, value.getFirstTimestamp());
        stmt.bindLong(3, value.getLastTimestamp());
        if (value.getDeviceAddress() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDeviceAddress());
        }
        stmt.bindLong(5, value.getRssi());
        stmt.bindLong(6, value.getTx());
        if (value.getDeviceProtocol() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDeviceProtocol());
        }
        if (value.getPublicKey() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getPublicKey());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM device";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final Device... devices) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDevice.insert(devices);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final Device device) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDevice.insert(device);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Device device) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDevice.handle(device);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Device device) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDevice.handle(device);
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
  public List<Device> getAllBLEDevices() {
    final String _sql = "SELECT * FROM device";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "publicKey");
      final int _cursorIndexOfFirstTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "first_timestamp");
      final int _cursorIndexOfLastTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "last_timestamp");
      final int _cursorIndexOfDeviceAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "device_address");
      final int _cursorIndexOfRssi = CursorUtil.getColumnIndexOrThrow(_cursor, "rssi");
      final int _cursorIndexOfTx = CursorUtil.getColumnIndexOrThrow(_cursor, "tx");
      final int _cursorIndexOfDeviceProtocol = CursorUtil.getColumnIndexOrThrow(_cursor, "device_protocol");
      final List<Device> _result = new ArrayList<Device>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Device _item;
        _item = new Device();
        final String _tmpPublicKey;
        _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
        _item.setPublicKey(_tmpPublicKey);
        final long _tmpFirstTimestamp;
        _tmpFirstTimestamp = _cursor.getLong(_cursorIndexOfFirstTimestamp);
        _item.setFirstTimestamp(_tmpFirstTimestamp);
        final long _tmpLastTimestamp;
        _tmpLastTimestamp = _cursor.getLong(_cursorIndexOfLastTimestamp);
        _item.setLastTimestamp(_tmpLastTimestamp);
        final String _tmpDeviceAddress;
        _tmpDeviceAddress = _cursor.getString(_cursorIndexOfDeviceAddress);
        _item.setDeviceAddress(_tmpDeviceAddress);
        final int _tmpRssi;
        _tmpRssi = _cursor.getInt(_cursorIndexOfRssi);
        _item.setRssi(_tmpRssi);
        final int _tmpTx;
        _tmpTx = _cursor.getInt(_cursorIndexOfTx);
        _item.setTx(_tmpTx);
        final String _tmpDeviceProtocol;
        _tmpDeviceProtocol = _cursor.getString(_cursorIndexOfDeviceProtocol);
        _item.setDeviceProtocol(_tmpDeviceProtocol);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Device> getDeviceByKeys(final String[] publicKey) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT ");
    _stringBuilder.append("*");
    _stringBuilder.append(" FROM device WHERE publicKey IN (");
    final int _inputSize = publicKey.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : publicKey) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "publicKey");
      final int _cursorIndexOfFirstTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "first_timestamp");
      final int _cursorIndexOfLastTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "last_timestamp");
      final int _cursorIndexOfDeviceAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "device_address");
      final int _cursorIndexOfRssi = CursorUtil.getColumnIndexOrThrow(_cursor, "rssi");
      final int _cursorIndexOfTx = CursorUtil.getColumnIndexOrThrow(_cursor, "tx");
      final int _cursorIndexOfDeviceProtocol = CursorUtil.getColumnIndexOrThrow(_cursor, "device_protocol");
      final List<Device> _result = new ArrayList<Device>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Device _item_1;
        _item_1 = new Device();
        final String _tmpPublicKey;
        _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
        _item_1.setPublicKey(_tmpPublicKey);
        final long _tmpFirstTimestamp;
        _tmpFirstTimestamp = _cursor.getLong(_cursorIndexOfFirstTimestamp);
        _item_1.setFirstTimestamp(_tmpFirstTimestamp);
        final long _tmpLastTimestamp;
        _tmpLastTimestamp = _cursor.getLong(_cursorIndexOfLastTimestamp);
        _item_1.setLastTimestamp(_tmpLastTimestamp);
        final String _tmpDeviceAddress;
        _tmpDeviceAddress = _cursor.getString(_cursorIndexOfDeviceAddress);
        _item_1.setDeviceAddress(_tmpDeviceAddress);
        final int _tmpRssi;
        _tmpRssi = _cursor.getInt(_cursorIndexOfRssi);
        _item_1.setRssi(_tmpRssi);
        final int _tmpTx;
        _tmpTx = _cursor.getInt(_cursorIndexOfTx);
        _item_1.setTx(_tmpTx);
        final String _tmpDeviceProtocol;
        _tmpDeviceProtocol = _cursor.getString(_cursorIndexOfDeviceProtocol);
        _item_1.setDeviceProtocol(_tmpDeviceProtocol);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Device getDeviceByKey(final String publicKey) {
    final String _sql = "SELECT * FROM device WHERE publicKey = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (publicKey == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, publicKey);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "publicKey");
      final int _cursorIndexOfFirstTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "first_timestamp");
      final int _cursorIndexOfLastTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "last_timestamp");
      final int _cursorIndexOfDeviceAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "device_address");
      final int _cursorIndexOfRssi = CursorUtil.getColumnIndexOrThrow(_cursor, "rssi");
      final int _cursorIndexOfTx = CursorUtil.getColumnIndexOrThrow(_cursor, "tx");
      final int _cursorIndexOfDeviceProtocol = CursorUtil.getColumnIndexOrThrow(_cursor, "device_protocol");
      final Device _result;
      if(_cursor.moveToFirst()) {
        _result = new Device();
        final String _tmpPublicKey;
        _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
        _result.setPublicKey(_tmpPublicKey);
        final long _tmpFirstTimestamp;
        _tmpFirstTimestamp = _cursor.getLong(_cursorIndexOfFirstTimestamp);
        _result.setFirstTimestamp(_tmpFirstTimestamp);
        final long _tmpLastTimestamp;
        _tmpLastTimestamp = _cursor.getLong(_cursorIndexOfLastTimestamp);
        _result.setLastTimestamp(_tmpLastTimestamp);
        final String _tmpDeviceAddress;
        _tmpDeviceAddress = _cursor.getString(_cursorIndexOfDeviceAddress);
        _result.setDeviceAddress(_tmpDeviceAddress);
        final int _tmpRssi;
        _tmpRssi = _cursor.getInt(_cursorIndexOfRssi);
        _result.setRssi(_tmpRssi);
        final int _tmpTx;
        _tmpTx = _cursor.getInt(_cursorIndexOfTx);
        _result.setTx(_tmpTx);
        final String _tmpDeviceProtocol;
        _tmpDeviceProtocol = _cursor.getString(_cursorIndexOfDeviceProtocol);
        _result.setDeviceProtocol(_tmpDeviceProtocol);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
