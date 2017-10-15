package com.tomasovych.filip.todolistplayground.model.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.tomasovych.filip.todolistplayground.model.Task;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import java.util.List;

@Dao
public interface TasksDao {

  @Query("SELECT * FROM task")
  Flowable<List<Task>> getTasks();

  @Query("SELECT * FROM task WHERE id = :id")
  Flowable<Task> getTask(long id);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long insertTask(Task task);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insertTasks(List<Task> tasks);

  @Delete
  void deleteTask(Task task);

}
