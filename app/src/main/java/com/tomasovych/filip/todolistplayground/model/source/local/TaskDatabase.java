package com.tomasovych.filip.todolistplayground.model.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.tomasovych.filip.todolistplayground.model.Task;
import javax.inject.Singleton;

@Singleton
@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

  public abstract TasksDao tasksDao();
}
