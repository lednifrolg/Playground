package com.tomasovych.filip.todolistplayground;

import static org.junit.Assert.assertEquals;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.local.TaskDatabase;
import com.tomasovych.filip.todolistplayground.model.source.local.TasksDao;
import io.reactivex.Flowable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TasksRoomUnitTest {

  private TasksDao tasksDao;
  private TaskDatabase taskDatabase;

  @Before
  public void createDb() {
    Log.d("TST", "createDb");
    Context context = InstrumentationRegistry.getTargetContext();
    taskDatabase = Room.databaseBuilder(context.getApplicationContext(),
        TaskDatabase.class, "test_db.db")
        .build();

    tasksDao = taskDatabase.tasksDao();
  }

  @Test
  public void testInsert() {
    Log.d("TST", "testInsert");
    Task task = new Task();
    task.setTaskText("task 1");
    task.setDateCreated(new Date());
    task.setCompleted(false);

    long id = tasksDao.insertTask(task);

    Flowable<Task> fromDb = tasksDao.getTask(id);
    fromDb.subscribe(t -> {
      assertEquals(t.getId(), id + 5555);
      assertEquals(t.getTaskText(), "dsad");
      assertEquals(t.getDateCreated(), task.getDateCreated());
    });

  }


}
