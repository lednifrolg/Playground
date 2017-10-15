package com.tomasovych.filip.todolistplayground.model.source;

import static com.tomasovych.filip.todolistplayground.tasks.TasksActivity.TAG;

import android.util.Log;
import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.local.TaskDatabase;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class TaskRepositoryImpl implements TaskRepository {

  public static final String TAG = TaskRepositoryImpl.class.getSimpleName();

  private TaskDatabase taskDatabase;

  @Inject
  public TaskRepositoryImpl(TaskDatabase taskDatabase) {
    Log.d(TAG, "cotr: " + this.hashCode());
    Log.d(TAG, "TaskDatabase: " + taskDatabase);

    this.taskDatabase = taskDatabase;
  }

  @Override
  public Flowable<List<Task>> loadTasks() {
    return taskDatabase.tasksDao().getTasks();
  }

  @Override
  public Observable<Long> saveTask(Task task) {
    return Observable.fromCallable(() -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return taskDatabase.tasksDao().insertTask(task);
    });
  }
}
