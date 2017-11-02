package com.tomasovych.filip.todolistplayground.newtask;

public interface CreateTasksContract {

  interface CreateTaskPresenter {

    void attachView(CreateTaskView view);

    void detachView();

    void onBindTasksItemView(TaskItemsView taskItemsView, int position);

    void itemClicked(int position);

    void itemRemoved(int position);
  }

  interface CreateTaskView {

    void setListSize(int listSize);

    void notifyDataChanged();
    // TODO
  }

  public interface TaskItemsView {

    void setItemName(String itemName);

    void showAnimation();

    void setChecked(boolean checked);

  }

}
