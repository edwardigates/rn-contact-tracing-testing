package com.wix.specialble.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wix.specialble.bt.Scan;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ScanDao_Impl implements ScanDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Scan> __insertionAdapterOfScan;

  private final EntityDeletionOrUpdateAdapter<Scan> __deletionAdapterOfScan;

  private final EntityDeletionOrUpdateAdapter<Scan> __updateAdapterOfScan;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public ScanDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScan = new EntityInsertionAdapter<Scan>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `scans` (`id`,`publicKey`,`timestamp`,`scan_address`,`rssi`,`tx`,`scan_protocol`,`proximity`,`acceleration_x`,`acceleration_y`,`acceleration_z`,`rotation_x`,`rotation_y`,`rotation_z`,`rotation_scalar`,`battery_level`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Scan value) {
        stmt.bindLong(1, value.getId());
        if (value.getPublicKey() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPublicKey());
        }
        stmt.bindLong(3, value.getTimestamp());
        if (value.getScanAddress() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getScanAddress());
        }
        stmt.bindLong(5, value.getRssi());
        stmt.bindLong(6, value.getTx());
        if (value.getScanProtocol() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getScanProtocol());
        }
        stmt.bindDouble(8, value.getProximityValue());
        stmt.bindDouble(9, value.getAccelerationX());
        stmt.bindDouble(10, value.getAccelerationY());
        stmt.bindDouble(11, value.getAccelerationZ());
        stmt.bindDouble(12, value.getRotationVectorX());
        stmt.bindDouble(13, value.getRotationVectorY());
        stmt.bindDouble(14, value.getRotationVectorZ());
        stmt.bindDouble(15, value.getRotationVectorScalar());
        stmt.bindLong(16, value.getBatteryLevel());
      }
    };
    this.__deletionAdapterOfScan = new EntityDeletionOrUpdateAdapter<Scan>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `scans` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Scan value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfScan = new EntityDeletionOrUpdateAdapter<Scan>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `scans` SET `id` = ?,`publicKey` = ?,`timestamp` = ?,`scan_address` = ?,`rssi` = ?,`tx` = ?,`scan_protocol` = ?,`proximity` = ?,`acceleration_x` = ?,`acceleration_y` = ?,`acceleration_z` = ?,`rotation_x` = ?,`rotation_y` = ?,`rotation_z` = ?,`rotation_scalar` = ?,`battery_level` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Scan value) {
        stmt.bindLong(1, value.getId());
        if (value.getPublicKey() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPublicKey());
        }
        stmt.bindLong(3, value.getTimestamp());
        if (value.getScanAddress() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getScanAddress());
        }
        stmt.bindLong(5, value.getRssi());
        stmt.bindLong(6, value.getTx());
        if (value.getScanProtocol() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getScanProtocol());
        }
        stmt.bindDouble(8, value.getProximityValue());
        stmt.bindDouble(9, value.getAccelerationX());
        stmt.bindDouble(10, value.getAccelerationY());
        stmt.bindDouble(11, value.getAccelerationZ());
        stmt.bindDouble(12, value.getRotationVectorX());
        stmt.bindDouble(13, value.getRotationVectorY());
        stmt.bindDouble(14, value.getRotationVectorZ());
        stmt.bindDouble(15, value.getRotationVectorScalar());
        stmt.bindLong(16, value.getBatteryLevel());
        stmt.bindLong(17, value.getId());
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM scans";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final Scan... scans) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfScan.insert(scans);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final Scan scan) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfScan.insert(scan);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Scan scan) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfScan.handle(scan);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Scan scan) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfScan.handle(scan);
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
  public List<Scan> getAllBLEScans() {
    final String _sql = "SELECT * FROM scans";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "publicKey");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfScanAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_address");
      final int _cursorIndexOfRssi = CursorUtil.getColumnIndexOrThrow(_cursor, "rssi");
      final int _cursorIndexOfTx = CursorUtil.getColumnIndexOrThrow(_cursor, "tx");
      final int _cursorIndexOfScanProtocol = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_protocol");
      final int _cursorIndexOfProximityValue = CursorUtil.getColumnIndexOrThrow(_cursor, "proximity");
      final int _cursorIndexOfAccelerationX = CursorUtil.getColumnIndexOrThrow(_cursor, "acceleration_x");
      final int _cursorIndexOfAccelerationY = CursorUtil.getColumnIndexOrThrow(_cursor, "acceleration_y");
      final int _cursorIndexOfAccelerationZ = CursorUtil.getColumnIndexOrThrow(_cursor, "acceleration_z");
      final int _cursorIndexOfRotationVectorX = CursorUtil.getColumnIndexOrThrow(_cursor, "rotation_x");
      final int _cursorIndexOfRotationVectorY = CursorUtil.getColumnIndexOrThrow(_cursor, "rotation_y");
      final int _cursorIndexOfRotationVectorZ = CursorUtil.getColumnIndexOrThrow(_cursor, "rotation_z");
      final int _cursorIndexOfRotationVectorScalar = CursorUtil.getColumnIndexOrThrow(_cursor, "rotation_scalar");
      final int _cursorIndexOfBatteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "battery_level");
      final List<Scan> _result = new ArrayList<Scan>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Scan _item;
        _item = new Scan();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpPublicKey;
        _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
        _item.setPublicKey(_tmpPublicKey);
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _item.setTimestamp(_tmpTimestamp);
        final String _tmpScanAddress;
        _tmpScanAddress = _cursor.getString(_cursorIndexOfScanAddress);
        _item.setScanAddress(_tmpScanAddress);
        final int _tmpRssi;
        _tmpRssi = _cursor.getInt(_cursorIndexOfRssi);
        _item.setRssi(_tmpRssi);
        final int _tmpTx;
        _tmpTx = _cursor.getInt(_cursorIndexOfTx);
        _item.setTx(_tmpTx);
        final String _tmpScanProtocol;
        _tmpScanProtocol = _cursor.getString(_cursorIndexOfScanProtocol);
        _item.setScanProtocol(_tmpScanProtocol);
        final float _tmpProximityValue;
        _tmpProximityValue = _cursor.getFloat(_cursorIndexOfProximityValue);
        _item.setProximityValue(_tmpProximityValue);
        final float _tmpAccelerationX;
        _tmpAccelerationX = _cursor.getFloat(_cursorIndexOfAccelerationX);
        _item.setAccelerationX(_tmpAccelerationX);
        final float _tmpAccelerationY;
        _tmpAccelerationY = _cursor.getFloat(_cursorIndexOfAccelerationY);
        _item.setAccelerationY(_tmpAccelerationY);
        final float _tmpAccelerationZ;
        _tmpAccelerationZ = _cursor.getFloat(_cursorIndexOfAccelerationZ);
        _item.setAccelerationZ(_tmpAccelerationZ);
        final float _tmpRotationVectorX;
        _tmpRotationVectorX = _cursor.getFloat(_cursorIndexOfRotationVectorX);
        _item.setRotationVectorX(_tmpRotationVectorX);
        final float _tmpRotationVectorY;
        _tmpRotationVectorY = _cursor.getFloat(_cursorIndexOfRotationVectorY);
        _item.setRotationVectorY(_tmpRotationVectorY);
        final float _tmpRotationVectorZ;
        _tmpRotationVectorZ = _cursor.getFloat(_cursorIndexOfRotationVectorZ);
        _item.setRotationVectorZ(_tmpRotationVectorZ);
        final float _tmpRotationVectorScalar;
        _tmpRotationVectorScalar = _cursor.getFloat(_cursorIndexOfRotationVectorScalar);
        _item.setRotationVectorScalar(_tmpRotationVectorScalar);
        final int _tmpBatteryLevel;
        _tmpBatteryLevel = _cursor.getInt(_cursorIndexOfBatteryLevel);
        _item.setBatteryLevel(_tmpBatteryLevel);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Scan> getScansByKey(final String publicKey) {
    final String _sql = "SELECT * FROM scans WHERE publicKey = ? ORDER BY timestamp desc";
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
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "publicKey");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfScanAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_address");
      final int _cursorIndexOfRssi = CursorUtil.getColumnIndexOrThrow(_cursor, "rssi");
      final int _cursorIndexOfTx = CursorUtil.getColumnIndexOrThrow(_cursor, "tx");
      final int _cursorIndexOfScanProtocol = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_protocol");
      final int _cursorIndexOfProximityValue = CursorUtil.getColumnIndexOrThrow(_cursor, "proximity");
      final int _cursorIndexOfAccelerationX = CursorUtil.getColumnIndexOrThrow(_cursor, "acceleration_x");
      final int _cursorIndexOfAccelerationY = CursorUtil.getColumnIndexOrThrow(_cursor, "acceleration_y");
      final int _cursorIndexOfAccelerationZ = CursorUtil.getColumnIndexOrThrow(_cursor, "acceleration_z");
      final int _cursorIndexOfRotationVectorX = CursorUtil.getColumnIndexOrThrow(_cursor, "rotation_x");
      final int _cursorIndexOfRotationVectorY = CursorUtil.getColumnIndexOrThrow(_cursor, "rotation_y");
      final int _cursorIndexOfRotationVectorZ = CursorUtil.getColumnIndexOrThrow(_cursor, "rotation_z");
      final int _cursorIndexOfRotationVectorScalar = CursorUtil.getColumnIndexOrThrow(_cursor, "rotation_scalar");
      final int _cursorIndexOfBatteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "battery_level");
      final List<Scan> _result = new ArrayList<Scan>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Scan _item;
        _item = new Scan();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpPublicKey;
        _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
        _item.setPublicKey(_tmpPublicKey);
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _item.setTimestamp(_tmpTimestamp);
        final String _tmpScanAddress;
        _tmpScanAddress = _cursor.getString(_cursorIndexOfScanAddress);
        _item.setScanAddress(_tmpScanAddress);
        final int _tmpRssi;
        _tmpRssi = _cursor.getInt(_cursorIndexOfRssi);
        _item.setRssi(_tmpRssi);
        final int _tmpTx;
        _tmpTx = _cursor.getInt(_cursorIndexOfTx);
        _item.setTx(_tmpTx);
        final String _tmpScanProtocol;
        _tmpScanProtocol = _cursor.getString(_cursorIndexOfScanProtocol);
        _item.setScanProtocol(_tmpScanProtocol);
        final float _tmpProximityValue;
        _tmpProximityValue = _cursor.getFloat(_cursorIndexOfProximityValue);
        _item.setProximityValue(_tmpProximityValue);
        final float _tmpAccelerationX;
        _tmpAccelerationX = _cursor.getFloat(_cursorIndexOfAccelerationX);
        _item.setAccelerationX(_tmpAccelerationX);
        final float _tmpAccelerationY;
        _tmpAccelerationY = _cursor.getFloat(_cursorIndexOfAccelerationY);
        _item.setAccelerationY(_tmpAccelerationY);
        final float _tmpAccelerationZ;
        _tmpAccelerationZ = _cursor.getFloat(_cursorIndexOfAccelerationZ);
        _item.setAccelerationZ(_tmpAccelerationZ);
        final float _tmpRotationVectorX;
        _tmpRotationVectorX = _cursor.getFloat(_cursorIndexOfRotationVectorX);
        _item.setRotationVectorX(_tmpRotationVectorX);
        final float _tmpRotationVectorY;
        _tmpRotationVectorY = _cursor.getFloat(_cursorIndexOfRotationVectorY);
        _item.setRotationVectorY(_tmpRotationVectorY);
        final float _tmpRotationVectorZ;
        _tmpRotationVectorZ = _cursor.getFloat(_cursorIndexOfRotationVectorZ);
        _item.setRotationVectorZ(_tmpRotationVectorZ);
        final float _tmpRotationVectorScalar;
        _tmpRotationVectorScalar = _cursor.getFloat(_cursorIndexOfRotationVectorScalar);
        _item.setRotationVectorScalar(_tmpRotationVectorScalar);
        final int _tmpBatteryLevel;
        _tmpBatteryLevel = _cursor.getInt(_cursorIndexOfBatteryLevel);
        _item.setBatteryLevel(_tmpBatteryLevel);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
