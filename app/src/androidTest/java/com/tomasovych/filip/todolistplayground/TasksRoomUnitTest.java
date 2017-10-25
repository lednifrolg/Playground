package com.tomasovych.filip.todolistplayground;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.local.TaskDatabase;
import com.tomasovych.filip.todolistplayground.model.source.local.TasksDao;
import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.TestSubscriber;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
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
    taskDatabase = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
        TaskDatabase.class).allowMainThreadQueries()
        .build();

    tasksDao = taskDatabase.tasksDao();
  }

  @After
  public void closeDb() throws Exception {
    taskDatabase.close();
  }

  @Test
  public void testInsert() {
    TestSubscriber<Task> testSubscriber = tasksDao.getTask(0).test();

    Log.d("TST", "testInsert");
    Task task = new Task();
    task.setTaskText("task 1");
    task.setDateCreated(new Date());
    task.setCompleted(false);

    long id = tasksDao.insertTask(task);


    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    testSubscriber.assertNoErrors();
    testSubscriber.assertNotComplete();
    testSubscriber.assertValue(new Predicate<Task>() {
      @Override
      public boolean test(Task task1) throws Exception {
        return task1.getTaskText().equals("task 1");
      }
    });
  }


}
