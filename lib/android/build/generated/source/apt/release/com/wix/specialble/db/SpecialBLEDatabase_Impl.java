package com.wix.specialble.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SpecialBLEDatabase_Impl extends SpecialBLEDatabase {
  private volatile DeviceDao _deviceDao;

  private volatile ScanDao _scanDao;

  private volatile EventDao _eventDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Device` (`publicKey` TEXT NOT NULL, `first_timestamp` INTEGER NOT NULL, `last_timestamp` INTEGER NOT NULL, `device_address` TEXT, `rssi` INTEGER NOT NULL, `tx` INTEGER NOT NULL, `device_protocol` TEXT, PRIMARY KEY(`publicKey`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `scans` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `publicKey` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `scan_address` TEXT, `rssi` INTEGER NOT NULL, `tx` INTEGER NOT NULL, `scan_protocol` TEXT, `proximity` REAL NOT NULL, `acceleration_x` REAL NOT NULL, `acceleration_y` REAL NOT NULL, `acceleration_z` REAL NOT NULL, `rotation_x` REAL NOT NULL, `rotation_y` REAL NOT NULL, `rotation_z` REAL NOT NULL, `rotation_scalar` REAL NOT NULL, `battery_level` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `device_name` TEXT, `action_type` TEXT, `success` TEXT, `errorMessage` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a798348e1d5369e8a0ad48b7c3f5c8ac')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Device`");
        _db.execSQL("DROP TABLE IF EXISTS `scans`");
        _db.execSQL("DROP TABLE IF EXISTS `events`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDevice = new HashMap<String, TableInfo.Column>(7);
        _columnsDevice.put("publicKey", new TableInfo.Column("publicKey", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevice.put("first_timestamp", new TableInfo.Column("first_timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevice.put("last_timestamp", new TableInfo.Column("last_timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevice.put("device_address", new TableInfo.Column("device_address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevice.put("rssi", new TableInfo.Column("rssi", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevice.put("tx", new TableInfo.Column("tx", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevice.put("device_protocol", new TableInfo.Column("device_protocol", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDevice = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDevice = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDevice = new TableInfo("Device", _columnsDevice, _foreignKeysDevice, _indicesDevice);
        final TableInfo _existingDevice = TableInfo.read(_db, "Device");
        if (! _infoDevice.equals(_existingDevice)) {
          return new RoomOpenHelper.ValidationResult(false, "Device(com.wix.specialble.bt.Device).\n"
                  + " Expected:\n" + _infoDevice + "\n"
                  + " Found:\n" + _existingDevice);
        }
        final HashMap<String, TableInfo.Column> _columnsScans = new HashMap<String, TableInfo.Column>(16);
        _columnsScans.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("publicKey", new TableInfo.Column("publicKey", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("scan_address", new TableInfo.Column("scan_address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("rssi", new TableInfo.Column("rssi", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("tx", new TableInfo.Column("tx", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("scan_protocol", new TableInfo.Column("scan_protocol", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("proximity", new TableInfo.Column("proximity", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("acceleration_x", new TableInfo.Column("acceleration_x", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("acceleration_y", new TableInfo.Column("acceleration_y", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("acceleration_z", new TableInfo.Column("acceleration_z", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("rotation_x", new TableInfo.Column("rotation_x", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("rotation_y", new TableInfo.Column("rotation_y", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("rotation_z", new TableInfo.Column("rotation_z", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("rotation_scalar", new TableInfo.Column("rotation_scalar", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScans.put("battery_level", new TableInfo.Column("battery_level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScans = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScans = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoScans = new TableInfo("scans", _columnsScans, _foreignKeysScans, _indicesScans);
        final TableInfo _existingScans = TableInfo.read(_db, "scans");
        if (! _infoScans.equals(_existingScans)) {
          return new RoomOpenHelper.ValidationResult(false, "scans(com.wix.specialble.bt.Scan).\n"
                  + " Expected:\n" + _infoScans + "\n"
                  + " Found:\n" + _existingScans);
        }
        final HashMap<String, TableInfo.Column> _columnsEvents = new HashMap<String, TableInfo.Column>(6);
        _columnsEvents.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("device_name", new TableInfo.Column("device_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("action_type", new TableInfo.Column("action_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("success", new TableInfo.Column("success", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("errorMessage", new TableInfo.Column("errorMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEvents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEvents = new TableInfo("events", _columnsEvents, _foreignKeysEvents, _indicesEvents);
        final TableInfo _existingEvents = TableInfo.read(_db, "events");
        if (! _infoEvents.equals(_existingEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "events(com.wix.specialble.bt.Event).\n"
                  + " Expected:\n" + _infoEvents + "\n"
                  + " Found:\n" + _existingEvents);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a798348e1d5369e8a0ad48b7c3f5c8ac", "4756308bbb70f5cfd142bc5d6d88b938");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Device","scans","events");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Device`");
      _db.execSQL("DELETE FROM `scans`");
      _db.execSQL("DELETE FROM `events`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DeviceDao deviceDao() {
    if (_deviceDao != null) {
      return _deviceDao;
    } else {
      synchronized(this) {
        if(_deviceDao == null) {
          _deviceDao = new DeviceDao_Impl(this);
        }
        return _deviceDao;
      }
    }
  }

  @Override
  public ScanDao scanDao() {
    if (_scanDao != null) {
      return _scanDao;
    } else {
      synchronized(this) {
        if(_scanDao == null) {
          _scanDao = new ScanDao_Impl(this);
        }
        return _scanDao;
      }
    }
  }

  @Override
  public EventDao eventDao() {
    if (_eventDao != null) {
      return _eventDao;
    } else {
      synchronized(this) {
        if(_eventDao == null) {
          _eventDao = new EventDao_Impl(this);
        }
        return _eventDao;
      }
    }
  }
}
