package com.tomasovych.filip.todolistplayground.model.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.tomasovych.filip.todolistplayground.di.annotations.ApplicationContext;
import com.tomasovych.filip.todolistplayground.di.annotations.DatabaseInfo;
import com.tomasovych.filip.todolistplayground.model.Task;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

//  public static TaskDatabase INSTANCE;
//
//  @Inject
//  public TaskDatabase(@ApplicationContext Context context, @DatabaseInfo String taskDbName) {
//    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//        TaskDatabase.class, taskDbName)
//        .build();
//  }

  public abstract TasksDao tasksDao();
}
