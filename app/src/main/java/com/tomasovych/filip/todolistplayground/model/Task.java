package com.tomasovych.filip.todolistplayground.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import java.util.Date;

@Entity
public class Task {

  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "task_text")
  private String taskText;

  private boolean completed;

  @ColumnInfo(name = "date_created")
  @TypeConverters({Converters.class})
  private Date dateCreated;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTaskText() {
    return taskText;
  }

  public void setTaskText(String taskText) {
    this.taskText = taskText;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  @Override
  public String toString() {
    return "Taskid : " + id + " Text : " + taskText;
  }
}
