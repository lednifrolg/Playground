package com.tomasovych.filip.todolistplayground.di;

import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksPresenter;
import com.tomasovych.filip.todolistplayground.tasks.TasksPresenterImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class PresenterModule {

  @Provides
  @Singleton
  TasksPresenter provideTasksPresenter() {
    return new TasksPresenterImpl();
  }
}
