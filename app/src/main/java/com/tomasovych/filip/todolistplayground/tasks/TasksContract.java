package com.tomasovych.filip.todolistplayground.tasks;

public interface TasksContract {

  public interface TasksView {

    void showMessage(String message);
  }

  public interface TasksPresenter {

    void attachView(TasksView view);

    void detachView();

    void buttonClicked();
  }

}
