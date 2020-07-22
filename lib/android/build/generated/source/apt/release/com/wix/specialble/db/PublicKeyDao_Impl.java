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
import com.wix.specialble.kays.PublicKey;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PublicKeyDao_Impl implements PublicKeyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PublicKey> __insertionAdapterOfPublicKey;

  private final EntityDeletionOrUpdateAdapter<PublicKey> __deletionAdapterOfPublicKey;

  private final EntityDeletionOrUpdateAdapter<PublicKey> __updateAdapterOfPublicKey;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public PublicKeyDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPublicKey = new EntityInsertionAdapter<PublicKey>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `PublicKey` (`id`,`public_key`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PublicKey value) {
        stmt.bindLong(1, value.getId());
        if (value.getPublicKey() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPublicKey());
        }
      }
    };
    this.__deletionAdapterOfPublicKey = new EntityDeletionOrUpdateAdapter<PublicKey>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `PublicKey` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PublicKey value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPublicKey = new EntityDeletionOrUpdateAdapter<PublicKey>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `PublicKey` SET `id` = ?,`public_key` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PublicKey value) {
        stmt.bindLong(1, value.getId());
        if (value.getPublicKey() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPublicKey());
        }
        stmt.bindLong(3, value.getId());
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM publicKey";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final List<PublicKey> pks) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPublicKey.insert(pks);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final PublicKey publicKey) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPublicKey.insert(publicKey);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final PublicKey publicKey) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPublicKey.handle(publicKey);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final PublicKey publicKey) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPublicKey.handle(publicKey);
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
  public PublicKey getPKByIndex(final int index) {
    final String _sql = "SELECT * FROM publickey WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, index);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "public_key");
      final PublicKey _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpPublicKey;
        _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
        _result = new PublicKey(_tmpId,_tmpPublicKey);
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
