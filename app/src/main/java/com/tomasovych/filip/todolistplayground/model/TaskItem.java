package com.tomasovych.filip.todolistplayground.model;

import static android.arch.persistence.room.ForeignKey.CASCADE;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(
    foreignKeys = @ForeignKey(
        entity = Task.class,
        parentColumns = "id",
        childColumns = "taskId",
        onUpdate = CASCADE
    )
)
public class TaskItem {

  @PrimaryKey(autoGenerate = true)
  private long id;

  private String name;

  private boolean completed;

  private long taskId;

  public TaskItem(long id, String name, boolean completed, long taskId) {
    this.id = id;
    this.name = name;
    this.completed = completed;
    this.taskId = taskId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public long getTaskId() {
    return taskId;
  }

  public void setTaskId(long taskId) {
    this.taskId = taskId;
  }
}
