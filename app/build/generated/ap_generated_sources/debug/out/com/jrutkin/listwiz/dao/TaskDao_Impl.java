package com.jrutkin.listwiz.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jrutkin.listwiz.models.TaskModel;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TaskModel> __insertionAdapterOfTaskModel;

  private final EntityDeletionOrUpdateAdapter<TaskModel> __deletionAdapterOfTaskModel;

  private final EntityDeletionOrUpdateAdapter<TaskModel> __updateAdapterOfTaskModel;

  public TaskDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTaskModel = new EntityInsertionAdapter<TaskModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TaskModel` (`id`,`taskName`,`taskDesc`,`taskStatus`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TaskModel value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.id);
        }
        if (value.getTaskName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTaskName());
        }
        if (value.getTaskDesc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTaskDesc());
        }
        if (value.getTaskStatus() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, __TaskStatusEnum_enumToString(value.getTaskStatus()));
        }
      }
    };
    this.__deletionAdapterOfTaskModel = new EntityDeletionOrUpdateAdapter<TaskModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `TaskModel` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TaskModel value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.id);
        }
      }
    };
    this.__updateAdapterOfTaskModel = new EntityDeletionOrUpdateAdapter<TaskModel>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TaskModel` SET `id` = ?,`taskName` = ?,`taskDesc` = ?,`taskStatus` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TaskModel value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.id);
        }
        if (value.getTaskName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTaskName());
        }
        if (value.getTaskDesc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTaskDesc());
        }
        if (value.getTaskStatus() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, __TaskStatusEnum_enumToString(value.getTaskStatus()));
        }
        if (value.id == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.id);
        }
      }
    };
  }

  @Override
  public void insertTask(final TaskModel task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTaskModel.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final TaskModel taskModel) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTaskModel.handle(taskModel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final TaskModel taskModel) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTaskModel.handle(taskModel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<TaskModel> findAll() {
    final String _sql = "SELECT * FROM TaskModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTaskName = CursorUtil.getColumnIndexOrThrow(_cursor, "taskName");
      final int _cursorIndexOfTaskDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "taskDesc");
      final int _cursorIndexOfTaskStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "taskStatus");
      final List<TaskModel> _result = new ArrayList<TaskModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TaskModel _item;
        _item = new TaskModel();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        final String _tmpTaskName;
        if (_cursor.isNull(_cursorIndexOfTaskName)) {
          _tmpTaskName = null;
        } else {
          _tmpTaskName = _cursor.getString(_cursorIndexOfTaskName);
        }
        _item.setTaskName(_tmpTaskName);
        final String _tmpTaskDesc;
        if (_cursor.isNull(_cursorIndexOfTaskDesc)) {
          _tmpTaskDesc = null;
        } else {
          _tmpTaskDesc = _cursor.getString(_cursorIndexOfTaskDesc);
        }
        _item.setTaskDesc(_tmpTaskDesc);
        final TaskModel.TaskStatusEnum _tmpTaskStatus;
        _tmpTaskStatus = __TaskStatusEnum_stringToEnum(_cursor.getString(_cursorIndexOfTaskStatus));
        _item.setTaskStatus(_tmpTaskStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public TaskModel findById(final long id) {
    final String _sql = "SELECT * FROM TaskModel WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTaskName = CursorUtil.getColumnIndexOrThrow(_cursor, "taskName");
      final int _cursorIndexOfTaskDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "taskDesc");
      final int _cursorIndexOfTaskStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "taskStatus");
      final TaskModel _result;
      if(_cursor.moveToFirst()) {
        _result = new TaskModel();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _result.id = null;
        } else {
          _result.id = _cursor.getLong(_cursorIndexOfId);
        }
        final String _tmpTaskName;
        if (_cursor.isNull(_cursorIndexOfTaskName)) {
          _tmpTaskName = null;
        } else {
          _tmpTaskName = _cursor.getString(_cursorIndexOfTaskName);
        }
        _result.setTaskName(_tmpTaskName);
        final String _tmpTaskDesc;
        if (_cursor.isNull(_cursorIndexOfTaskDesc)) {
          _tmpTaskDesc = null;
        } else {
          _tmpTaskDesc = _cursor.getString(_cursorIndexOfTaskDesc);
        }
        _result.setTaskDesc(_tmpTaskDesc);
        final TaskModel.TaskStatusEnum _tmpTaskStatus;
        _tmpTaskStatus = __TaskStatusEnum_stringToEnum(_cursor.getString(_cursorIndexOfTaskStatus));
        _result.setTaskStatus(_tmpTaskStatus);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TaskModel> findByStatus(final TaskModel.TaskStatusEnum taskStatus) {
    final String _sql = "SELECT * FROM TaskModel WHERE taskStatus = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (taskStatus == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, __TaskStatusEnum_enumToString(taskStatus));
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTaskName = CursorUtil.getColumnIndexOrThrow(_cursor, "taskName");
      final int _cursorIndexOfTaskDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "taskDesc");
      final int _cursorIndexOfTaskStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "taskStatus");
      final List<TaskModel> _result = new ArrayList<TaskModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TaskModel _item;
        _item = new TaskModel();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        final String _tmpTaskName;
        if (_cursor.isNull(_cursorIndexOfTaskName)) {
          _tmpTaskName = null;
        } else {
          _tmpTaskName = _cursor.getString(_cursorIndexOfTaskName);
        }
        _item.setTaskName(_tmpTaskName);
        final String _tmpTaskDesc;
        if (_cursor.isNull(_cursorIndexOfTaskDesc)) {
          _tmpTaskDesc = null;
        } else {
          _tmpTaskDesc = _cursor.getString(_cursorIndexOfTaskDesc);
        }
        _item.setTaskDesc(_tmpTaskDesc);
        final TaskModel.TaskStatusEnum _tmpTaskStatus;
        _tmpTaskStatus = __TaskStatusEnum_stringToEnum(_cursor.getString(_cursorIndexOfTaskStatus));
        _item.setTaskStatus(_tmpTaskStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __TaskStatusEnum_enumToString(final TaskModel.TaskStatusEnum _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case FRESH: return "FRESH";
      case FORGOTTEN: return "FORGOTTEN";
      case DOING: return "DOING";
      case COMPLETED: return "COMPLETED";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private TaskModel.TaskStatusEnum __TaskStatusEnum_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "FRESH": return TaskModel.TaskStatusEnum.FRESH;
      case "FORGOTTEN": return TaskModel.TaskStatusEnum.FORGOTTEN;
      case "DOING": return TaskModel.TaskStatusEnum.DOING;
      case "COMPLETED": return TaskModel.TaskStatusEnum.COMPLETED;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
