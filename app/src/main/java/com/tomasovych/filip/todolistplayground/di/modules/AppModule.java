package com.tomasovych.filip.todolistplayground.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.tomasovych.filip.todolistplayground.di.annotations.ApplicationContext;
import com.tomasovych.filip.todolistplayground.di.annotations.DatabaseInfo;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepositoryImpl;
import com.tomasovych.filip.todolistplayground.model.source.local.TaskDatabase;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

  private Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @ApplicationContext
  @Singleton
  Context provideContext() {
    return application;
  }

  @Provides
  @Singleton
  TaskDatabase provideTaskDatabase(@ApplicationContext Context context,
      @DatabaseInfo String taskDbName) {
    return Room.databaseBuilder(context.getApplicationContext(),
        TaskDatabase.class, taskDbName)
        .build();
  }

  @Provides
  @Singleton
  TaskRepository provideTaskRepository(TaskRepositoryImpl taskRepository) {
    return taskRepository;
  }

  @Provides
  @DatabaseInfo
  @Singleton
  String provideTaskDbName() {
    return "tasks.db";
  }


}
