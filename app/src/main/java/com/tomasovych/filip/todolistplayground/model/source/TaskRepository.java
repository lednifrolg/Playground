package com.tomasovych.filip.todolistplayground.model.source;

import com.tomasovych.filip.todolistplayground.model.Task;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import java.util.List;

public interface TaskRepository {

  Flowable<List<Task>> loadTasks();

  Observable<Long> saveTask(Task task);

  Flowable<Task> getTask(long id);

}
