package com.tomasovych.filip.todolistplayground.di.modules;

import android.app.Activity;
import android.content.Context;
import com.tomasovych.filip.todolistplayground.di.annotations.ActivityContext;
import com.tomasovych.filip.todolistplayground.di.annotations.PerActivity;
import com.tomasovych.filip.todolistplayground.newtask.CreateTaksPresenterImpl;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.CreateTaskPresenter;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsPresenter;
import com.tomasovych.filip.todolistplayground.newtask.TaskItemsPresenterImpl;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksListPresenter;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksPresenter;
import com.tomasovych.filip.todolistplayground.tasks.TasksListPresenterImpl;
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
  Activity provideActivity() {
    return activity;
  }

  @Provides
  @PerActivity
  TasksPresenter provideTaskPresenter(TasksPresenterImpl tasksPresenter) {
    return tasksPresenter;
  }

  @Provides
  @PerActivity
  TasksListPresenter provideTaskItemPresenter(TasksListPresenterImpl tasksListPresenter) {
    return tasksListPresenter;
  }

  @Provides
  @PerActivity
  CreateTaskPresenter provideCreateTaskPresenter(CreateTaksPresenterImpl createTaksPresenter) {
    return createTaksPresenter;
  }

  @Provides
  @PerActivity
  TaskItemsPresenter provideTaskItemsPresenter(TaskItemsPresenterImpl taskItemsPresenter) {
    return taskItemsPresenter;
  }
}
