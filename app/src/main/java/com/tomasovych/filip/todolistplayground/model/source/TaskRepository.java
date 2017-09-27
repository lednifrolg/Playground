package com.tomasovych.filip.todolistplayground.model.source;

import android.arch.lifecycle.LiveData;
import com.tomasovych.filip.todolistplayground.model.Task;
import java.util.List;

public interface TaskRepository {

  LiveData<List<Task>> loadTasks();

  void saveTask(Task task);

}
