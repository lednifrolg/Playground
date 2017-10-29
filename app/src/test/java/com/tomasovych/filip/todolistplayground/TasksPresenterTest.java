package com.tomasovych.filip.todolistplayground;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.tomasovych.filip.todolistplayground.model.Task;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksPresenter;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksView;
import com.tomasovych.filip.todolistplayground.tasks.TasksPresenterImpl;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class TasksPresenterTest {

  TaskRepository mockTaskRepository;
  TasksView mockView;
  TasksPresenter presenter;
  Task task;

  @Before
  public void setup() {
    mockTaskRepository = mock(TaskRepository.class);

    task = new Task();
    task.setId(1);
    task.setTaskText("Task one");
    task.setDateCreated(new Date());

    when(mockTaskRepository.getTask(anyLong())).thenReturn(Flowable.just(task));
    when(mockTaskRepository.loadTasks()).thenReturn(Flowable.just(new ArrayList<>()));

    mockView = mock(TasksView.class);

    presenter = new TasksPresenterImpl(mockTaskRepository);
    presenter.attachView(mockView);
  }

  @Test
  public void test() {
    presenter.createTaskButtonClicked();

    verifyZeroInteractions(mockView);
  }

}
