package com.tomasovych.filip.todolistplayground.tasks;

import android.util.Log;
import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksItemView;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksListAdapterView;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class TasksListPresenterImpl implements TasksContract.TasksListPresenter {

  public static final String TAG = TasksListPresenterImpl.class.getSimpleName();

  private TaskRepository taskRepository;
  private CompositeDisposable disposable;
  private List<Task> tasks;
  private TasksListAdapterView tasksListAdapterView;

  @Inject
  public TasksListPresenterImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
    disposable = new CompositeDisposable();
  }

  @Override
  public void onBindTasksItemView(TasksItemView tasksItemView, int position) {
    tasksItemView.setItemName(tasks.get(position).getTaskText());
  }

  @Override
  public void itemClicked(int position) {

  }

  @Override
  public void itemRemoved(int position) {

  }

  @Override
  public void attachView(TasksListAdapterView tasksListAdapterView) {
    this.tasksListAdapterView = tasksListAdapterView;

    disposable.add(taskRepository.loadTasks().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(t -> {
              tasks = t;
              tasksListAdapterView.setListSize(t.size());
              tasksListAdapterView.notifyDataChanged();
            },
            Throwable::printStackTrace,
            () -> {
            }));
  }

  @Override
  public void detachView() {
    Log.d(TAG, "detachView: ");
    tasksListAdapterView = null;
    disposable.clear();
  }
}
