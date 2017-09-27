package com.tomasovych.filip.todolistplayground.model.source;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.local.TaskDatabase;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

  @Override
  public LiveData<List<Task>> loadTasks() {
    return null;
  }

  @Override
  public void saveTask(Task task) {

  }
}
