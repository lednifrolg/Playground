package com.tomasovych.filip.todolistplayground.model.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.tomasovych.filip.todolistplayground.model.Task;
import java.util.List;

@Dao
public interface TasksDao {

  @Query("SELECT * FROM task")
  List<Task> getTasks();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertTask(Task task);

  @Delete
  void deleteTask(Task task);

}
