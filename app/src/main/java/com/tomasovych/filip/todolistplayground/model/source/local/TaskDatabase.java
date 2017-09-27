package com.tomasovych.filip.todolistplayground.model.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.tomasovych.filip.todolistplayground.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

  private static final Object sLock = new Object();
  private static TaskDatabase INSTANCE;

  public static TaskDatabase getInstance(Context context) {
    synchronized (sLock) {
      if (INSTANCE == null) {
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
            TaskDatabase.class, "Tasks.db")
            .build();
      }
      return INSTANCE;
    }
  }

  public abstract TasksDao tasksDao();
}
