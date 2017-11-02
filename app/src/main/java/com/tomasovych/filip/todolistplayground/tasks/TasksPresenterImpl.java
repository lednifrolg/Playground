package com.tomasovych.filip.todolistplayground.tasks;

import android.util.Log;
import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksItemView;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksPresenter;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class TasksPresenterImpl implements TasksPresenter {

  public static final String TAG = TasksPresenterImpl.class.getSimpleName();

  private TaskRepository taskRepository;
  private TasksView view;

  private List<Task> tasks;
  private CompositeDisposable disposable;

  @Inject
  public TasksPresenterImpl(TaskRepository taskRepository) {
    Log.d(TAG, "cotr: " + this.hashCode());
    Log.d(TAG, "taskRepository: " + taskRepository);

    this.taskRepository = taskRepository;
    disposable = new CompositeDisposable();
  }

  @Override
  public void attachView(TasksView view) {
    Log.d(TAG, "attachView: " + view);
    this.view = view;

    disposable.add(taskRepository.loadTasks().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(t -> {
              tasks = t;
              view.setListSize(t.size());
              view.notifyDataChanged();
              view.showMessage("Number of saved tasks : " + tasks.size());
            },
            Throwable::printStackTrace,
            () -> {
            }));
  }

  @Override
  public void createTaskButtonClicked() {
    view.startCreateTask();
  }

  @Override
  public void detachView() {
    disposable.clear();
    view = null;
  }

  @Override
  public void buttonClicked() {
    Log.d(TAG, "buttonClicked: ");
    Task task = new Task();
    task.setTaskText("Test ");
    //taskRepository.saveTask(task);

    disposable.add(
        taskRepository.saveTask(task).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())
            .subscribe(id -> Log.d(TAG, "id: " + id)));
  }

  @Override
  public void onBindTasksItemView(TasksItemView tasksItemView, int position) {
    tasksItemView.setItemName(tasks.get(position).getTaskText());
    tasksItemView.setItemDescription(
        "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna wirl aliqua. Up exlaborum incididunt. ");
    tasksItemView.setItemColor("#2BD2BB");
  }


  @Override
  public void itemClicked(int position) {
    view.showMessage("Item clicked at: " + position);
  }

  @Override
  public void itemRemoved(int position) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

}
