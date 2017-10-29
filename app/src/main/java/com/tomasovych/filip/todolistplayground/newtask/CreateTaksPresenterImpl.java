package com.tomasovych.filip.todolistplayground.newtask;

import android.util.Log;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.CreateTaskPresenter;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.CreateTaskView;
import javax.inject.Inject;

public class CreateTaksPresenterImpl implements CreateTaskPresenter {

  public static final String TAG = CreateTaksPresenterImpl.class.getSimpleName();

  private CreateTaskView view;

  private TaskRepository taskRepository;

  @Inject
  public CreateTaksPresenterImpl(TaskRepository taskRepository) {
    Log.d(TAG, "cotr: " + this.hashCode());

    this.taskRepository = taskRepository;
  }

  @Override
  public void attachView(CreateTaskView view) {
    this.view = view;
  }

  @Override
  public void detachView() {
    view = null;
  }
}
