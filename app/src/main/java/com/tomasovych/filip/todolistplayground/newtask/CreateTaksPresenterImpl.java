package com.tomasovych.filip.todolistplayground.newtask;

import android.util.Log;
import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.CreateTaskPresenter;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.CreateTaskView;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class CreateTaksPresenterImpl implements CreateTaskPresenter {

  public static final String TAG = CreateTaksPresenterImpl.class.getSimpleName();

  private CreateTaskView view;

  private TaskRepository taskRepository;
  private CompositeDisposable disposable;

  private List<Task> tasks;

  @Inject
  public CreateTaksPresenterImpl(TaskRepository taskRepository) {
    Log.d(TAG, "cotr: " + this.hashCode());
    disposable = new CompositeDisposable();
    this.taskRepository = taskRepository;
  }

  @Override
  public void attachView(CreateTaskView view) {
    this.view = view;

    disposable.add(taskRepository.loadTasks().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(t -> {
              tasks = t;
              view.setListSize(t.size());
              view.notifyDataChanged();
            },
            Throwable::printStackTrace,
            () -> {
            }));
  }

  @Override
  public void detachView() {
    disposable.clear();
    view = null;
  }

  @Override
  public void onBindTasksItemView(TaskItemsView taskItemsView, int position) {
    taskItemsView.setItemName("Task: " + position);
    taskItemsView.setChecked(tasks.get(position).isCompleted());
  }

  @Override
  public void itemClicked(int position) {
    Log.d(TAG, "itemClicked: " + position);
    if (tasks.get(position).isCompleted()) {
      tasks.get(position).setCompleted(false);
    } else {
      tasks.get(position).setCompleted(true);
    }
  }

  @Override
  public void itemRemoved(int position) {

  }
}
