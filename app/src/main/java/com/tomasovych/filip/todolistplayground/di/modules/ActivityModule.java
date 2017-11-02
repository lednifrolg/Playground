package com.tomasovych.filip.todolistplayground.di.modules;

import android.app.Activity;
import android.content.Context;
import com.tomasovych.filip.todolistplayground.di.annotations.ActivityContext;
import com.tomasovych.filip.todolistplayground.di.annotations.PerActivity;
import com.tomasovych.filip.todolistplayground.newtask.CreateTaksPresenterImpl;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.CreateTaskPresenter;
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
  @PerActivity
  Context provideContext() {
    return activity;
  }

  @Provides
  @PerActivity
  Activity provideTaskActivity() {
    return activity;
  }

  @Provides
  @PerActivity
  TasksPresenter provideTaskPresenter(TasksPresenterImpl tasksPresenter) {
    return tasksPresenter;
  }


  @Provides
  @PerActivity
  CreateTaskPresenter provideCreateTaskPresenter(CreateTaksPresenterImpl createTaksPresenter) {
    return createTaksPresenter;
  }

}
