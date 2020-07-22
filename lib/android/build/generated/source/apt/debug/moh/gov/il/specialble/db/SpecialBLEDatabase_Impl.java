package moh.gov.il.specialble.db;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SpecialBLEDatabase_Impl extends SpecialBLEDatabase {
  private volatile DeviceDao _deviceDao;

  private volatile ScanDao _scanDao;

  private volatile ContactDao _contactDao;

  private volatile EventDao _eventDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(6) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Device` (`publicKey` TEXT NOT NULL, `first_timestamp` INTEGER NOT NULL, `last_timestamp` INTEGER NOT NULL, `device_address` TEXT, `rssi` INTEGER NOT NULL, `tx` INTEGER NOT NULL, `device_protocol` TEXT, PRIMARY KEY(`publicKey`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `scans` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `publicKey` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `scan_address` TEXT, `rssi` INTEGER NOT NULL, `tx` INTEGER NOT NULL, `scan_protocol` TEXT, `proximity` REAL NOT NULL, `acceleration_x` REAL NOT NULL, `acceleration_y` REAL NOT NULL, `acceleration_z` REAL NOT NULL, `rotation_x` REAL NOT NULL, `rotation_y` REAL NOT NULL, `rotation_z` REAL NOT NULL, `rotation_scalar` REAL NOT NULL, `battery_level` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Contacts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ephemeral_id` BLOB NOT NULL, `rssi` BLOB, `timestamp` INTEGER NOT NULL, `geohash` BLOB, `user_id` BLOB, `lat` REAL NOT NULL, `lon` REAL NOT NULL)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_Contacts_ephemeral_id` ON `Contacts` (`ephemeral_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `device_name` TEXT, `action_type` TEXT, `success` TEXT, `errorMessage` TEXT, `battery` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0302ee67452e2a8ff6e9b876307bf8f3')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Device`");
        _db.execSQL("DROP TABLE IF EXISTS `scans`");
        _db.execSQL("DROP TABLE IF EXISTS `Contacts`");
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
          return new RoomOpenHelper.ValidationResult(false, "Device(moh.gov.il.specialble.bt.Device).\n"
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
          return new RoomOpenHelper.ValidationResult(false, "scans(moh.gov.il.specialble.bt.Scan).\n"
                  + " Expected:\n" + _infoScans + "\n"
                  + " Found:\n" + _existingScans);
        }
        final HashMap<String, TableInfo.Column> _columnsContacts = new HashMap<String, TableInfo.Column>(8);
        _columnsContacts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("ephemeral_id", new TableInfo.Column("ephemeral_id", "BLOB", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("rssi", new TableInfo.Column("rssi", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("geohash", new TableInfo.Column("geohash", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("user_id", new TableInfo.Column("user_id", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("lat", new TableInfo.Column("lat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("lon", new TableInfo.Column("lon", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContacts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContacts = new HashSet<TableInfo.Index>(1);
        _indicesContacts.add(new TableInfo.Index("index_Contacts_ephemeral_id", false, Arrays.asList("ephemeral_id")));
        final TableInfo _infoContacts = new TableInfo("Contacts", _columnsContacts, _foreignKeysContacts, _indicesContacts);
        final TableInfo _existingContacts = TableInfo.read(_db, "Contacts");
        if (! _infoContacts.equals(_existingContacts)) {
          return new RoomOpenHelper.ValidationResult(false, "Contacts(moh.gov.il.crypto.Contact).\n"
                  + " Expected:\n" + _infoContacts + "\n"
                  + " Found:\n" + _existingContacts);
        }
        final HashMap<String, TableInfo.Column> _columnsEvents = new HashMap<String, TableInfo.Column>(7);
        _columnsEvents.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("device_name", new TableInfo.Column("device_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("action_type", new TableInfo.Column("action_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("success", new TableInfo.Column("success", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("errorMessage", new TableInfo.Column("errorMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("battery", new TableInfo.Column("battery", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEvents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEvents = new TableInfo("events", _columnsEvents, _foreignKeysEvents, _indicesEvents);
        final TableInfo _existingEvents = TableInfo.read(_db, "events");
        if (! _infoEvents.equals(_existingEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "events(moh.gov.il.specialble.bt.Event).\n"
                  + " Expected:\n" + _infoEvents + "\n"
                  + " Found:\n" + _existingEvents);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "0302ee67452e2a8ff6e9b876307bf8f3", "7e5d87962e00b50a67cbc185badf27f2");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Device","scans","Contacts","events");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Device`");
      _db.execSQL("DELETE FROM `scans`");
      _db.execSQL("DELETE FROM `Contacts`");
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
  public ContactDao contactDao() {
    if (_contactDao != null) {
      return _contactDao;
    } else {
      synchronized(this) {
        if(_contactDao == null) {
          _contactDao = new ContactDao_Impl(this);
        }
        return _contactDao;
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
