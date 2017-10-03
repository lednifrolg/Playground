package com.tomasovych.filip.todolistplayground.di.modules;

import android.app.Activity;
import android.content.Context;
import com.tomasovych.filip.todolistplayground.TodoApplication;
import com.tomasovych.filip.todolistplayground.di.annotations.ActivityContext;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksPresenter;
import com.tomasovych.filip.todolistplayground.tasks.TasksPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  private Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides
  @ActivityContext
  Context provideContext() {
    return activity;
  }

  @Provides
  Activity provideActivity() {
    return activity;
  }

  @Provides
  TasksPresenter provideTaskPresenter(TaskRepository taskRepository) {
    return new TasksPresenterImpl(taskRepository);
  }

}
