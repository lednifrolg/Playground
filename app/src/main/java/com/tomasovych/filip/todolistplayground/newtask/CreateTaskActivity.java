package com.tomasovych.filip.todolistplayground.newtask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tomasovych.filip.todolistplayground.R;
import com.tomasovych.filip.todolistplayground.TodoApplication;
import com.tomasovych.filip.todolistplayground.base.BaseActivity;
import com.tomasovych.filip.todolistplayground.di.components.ActivityComponent;
import com.tomasovych.filip.todolistplayground.di.components.DaggerActivityComponent;
import com.tomasovych.filip.todolistplayground.di.modules.ActivityModule;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.CreateTaskPresenter;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.CreateTaskView;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsView;
import javax.inject.Inject;

public class CreateTaskActivity extends BaseActivity implements CreateTaskView {

  public static final String TAG = CreateTaskActivity.class.getSimpleName();

  @Inject
  CreateTaskPresenter createTaskPresenter;

  TaskItemsAdapter taskItemsAdapter;

  @BindView(R.id.rv_task_items)
  RecyclerView taskItemsRecyclerView;

  private ActivityComponent activityComponent;

  private ActivityComponent getActivityComponent() {
    if (activityComponent == null) {
      Log.d(TAG, "getActivityComponent: null");
      activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
          .appComponent(TodoApplication.get(this).getComponent())
          .build();
    }
    return activityComponent;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.item_activity);
    getActivityComponent().inject(this);
    ButterKnife.bind(this);

    getSupportActionBar();

    createTaskPresenter.attachView(this);
    initTasksRecyclerView();
  }

  private void initTasksRecyclerView() {
    if (taskItemsAdapter == null) {
      taskItemsAdapter = new TaskItemsAdapter(this);
    }

    taskItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    taskItemsRecyclerView.setAdapter(taskItemsAdapter);
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    taskItemsRecyclerView.setAdapter(null);
    createTaskPresenter.detachView();
  }

  @Override
  public void setListSize(int listSize) {
    taskItemsAdapter.setListSize(listSize);
  }

  @Override
  public void notifyDataChanged() {
    taskItemsAdapter.notifyDataChanged();
  }

  public void onBindTasksItemView(TaskItemsView taskItemsViewHolder, int position) {
    createTaskPresenter.onBindTasksItemView(taskItemsViewHolder, position);
  }

  public void itemClicked(int position) {
    createTaskPresenter.itemClicked(position);
  }
}
