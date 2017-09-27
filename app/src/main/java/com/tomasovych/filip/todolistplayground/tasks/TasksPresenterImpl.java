package com.tomasovych.filip.todolistplayground.tasks;

import android.util.Log;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksView;

public class TasksPresenterImpl implements TasksContract.TasksPresenter {

  public static final String TAG = TasksPresenterImpl.class.getSimpleName();
  private TasksView view;

  @Override
  public void attachView(TasksView view) {
    this.view = view;
  }

  @Override
  public void detachView() {
    view = null;
  }

  @Override
  public void buttonClicked() {
    view.showMessage("I was clicked");
    Log.d(TAG, "buttonClicked: ");
  }
}
