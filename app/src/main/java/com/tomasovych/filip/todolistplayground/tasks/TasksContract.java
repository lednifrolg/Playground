package com.tomasovych.filip.todolistplayground.tasks;

public interface TasksContract {

  public interface TasksView {

    void showMessage(String message);

    void startCreateTask();
  }

  public interface TasksPresenter {

    void attachView(TasksView view);

    void createTaskButtonClicked();

    void detachView();

    void buttonClicked();

  }

  public interface TasksItemView {
    void setItemName(String itemName);

    void setItemDescription(String description);

    void setItemColor(String color);

    void setPriority(int priority);

    void setState();
  }

  public interface TasksListPresenter {
    void onBindTasksItemView(TasksItemView tasksItemView, int position);

    void itemClicked(int position);

    void itemRemoved(int position);

    void attachView(TasksListAdapterView tasksListAdapterView);

    void detachView();
  }

  public interface TasksListAdapterView {
    void setListSize(int listSize);

    void notifyDataChanged();
  }
}
