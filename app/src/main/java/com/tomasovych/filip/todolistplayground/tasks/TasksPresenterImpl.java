package com.tomasovych.filip.todolistplayground.tasks;

import android.util.Log;
import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class TasksPresenterImpl implements TasksContract.TasksPresenter {

  public static final String TAG = TasksPresenterImpl.class.getSimpleName();
  int count = 0;
  private TaskRepository taskRepository;
  private TasksView view;

  @Inject
  public TasksPresenterImpl(TaskRepository taskRepository) {
    Log.d(TAG, "TasksPresenterImpl: cotr :" + taskRepository);
    this.taskRepository = taskRepository;
  }

  @Override
  public void attachView(TasksView view) {
    Log.d(TAG, "attachView: " + view);
    this.view = view;
  }

  @Override
  public void detachView() {
    view = null;
  }

  @Override
  public void buttonClicked() {
    Log.d(TAG, "buttonClicked: ");
    Task task = new Task();
    task.setTaskText("Test " + String.valueOf(count++));
    //taskRepository.saveTask(task);

    taskRepository.saveTask(task).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread())
        .subscribe(id -> Log.d(TAG, "id: " + id));

    taskRepository.loadTasks().filter(tasks1 -> !tasks1.isEmpty()).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(tasks1 -> view.showMessage("Number of entries: " + tasks1.size()));
  }
}
