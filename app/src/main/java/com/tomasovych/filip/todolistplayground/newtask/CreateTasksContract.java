package com.tomasovych.filip.todolistplayground.newtask;

public interface CreateTasksContract {

  interface CreateTaskPresenter {

    void attachView(CreateTaskView view);

    void detachView();
  }

  interface CreateTaskView {

  }

  public interface TaskItemsView {

    void setItemName(String itemName);

    void showAnimation();

    void setChecked(boolean checked);

    void setTag(int tag);
  }

  public interface TaskItemsPresenter {

    void onBindTasksItemView(TaskItemsView taskItemsView, int position);

    void itemClicked(int position);

    void itemRemoved(int position);

    void attachView(TaskItemsAdapterView taskItemsAdapterView);

    void detachView();
  }

  public interface TaskItemsAdapterView {

    void setListSize(int listSize);

    void notifyDataChanged();

    void notifyTaskItemChanged(int position);
  }


}
