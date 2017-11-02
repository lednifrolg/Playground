//package com.tomasovych.filip.todolistplayground.newtask;
//
//import com.tomasovych.filip.todolistplayground.model.Task;
//import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
//import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsAdapterView;
//import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsPresenter;
//import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsView;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.schedulers.Schedulers;
//import java.util.List;
//import javax.inject.Inject;
//
//public class TaskItemsPresenterImpl implements TaskItemsPresenter {
//
//  public static final String TAG = TaskItemsPresenterImpl.class.getSimpleName();
//
//  private TaskRepository taskRepository;
//  private CompositeDisposable disposable;
//  private TaskItemsAdapterView taskItemsAdapterView;
//
//  private List<Task> tasks;
//
//  @Inject
//  public TaskItemsPresenterImpl(TaskRepository taskRepository) {
//    this.taskRepository = taskRepository;
//    disposable = new CompositeDisposable();
//  }
//
//  @Override
//  public void onBindTasksItemView(TaskItemsView taskItemsView, int position) {
//    taskItemsView.setItemName("Task: " + position);
//    taskItemsView.setTag(position);
//    taskItemsView.setChecked(tasks.get(position).isCompleted());
//  }
//
//  @Override
//  public void itemClicked(int position) {
//    if (tasks.get(position).isCompleted()) {
//      tasks.get(position).setCompleted(false);
//    } else {
//      tasks.get(position).setCompleted(true);
//    }
//  }
//
//  @Override
//  public void itemRemoved(int position) {
//
//  }
//
//  @Override
//  public void attachView(TaskItemsAdapterView taskItemsAdapterView) {
//    this.taskItemsAdapterView = taskItemsAdapterView;
//
//    disposable.add(taskRepository.loadTasks().subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(t -> {
//              tasks = t;
//              taskItemsAdapterView.setListSize(t.size());
//              taskItemsAdapterView.notifyDataChanged();
//            },
//            Throwable::printStackTrace,
//            () -> {
//            }));
//  }
//
//  @Override
//  public void detachView() {
//    taskItemsAdapterView = null;
//    disposable.clear();
//  }
//}
